package com.youcode.gameyou.Provider.RegisterProvider;

import com.youcode.gameyou.DTO.ClientDTO;
import com.youcode.gameyou.Entity.Client;
import com.youcode.gameyou.Enum.Role;
import com.youcode.gameyou.Exception.ApiException;
import com.youcode.gameyou.Mapper.Mapper;
import com.youcode.gameyou.Provider.RegisterProvider.interfaces.IRegisterAsClientProvider;
import com.youcode.gameyou.Repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class RegisterAsClientProvider implements IRegisterAsClientProvider {
    private final Mapper<ClientDTO, Client> mapper;
    private final ClientRepository clientRepository;
    @Override
    public void register(ClientDTO registerRequestDTO) {
        if(clientRepository.findByEmail(registerRequestDTO.getEmail()).isPresent()) throw new ApiException("Email already taken", HttpStatus.BAD_REQUEST);
        registerRequestDTO.setIsActive(true);
        registerRequestDTO.setRole(Role.ROLE_CLIENT);
        registerRequestDTO.setCreatedAt(new Date());
        registerRequestDTO.setUpdatedAt(new Date());

        // map the DTO to the entity
        Client client = mapper.convertAtoB(registerRequestDTO, Client.class);
        // save the entity
        clientRepository.save(client);
    }
}