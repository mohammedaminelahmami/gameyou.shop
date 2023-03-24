package com.youcode.gameyou.Provider.RegisterProvider;

import com.youcode.gameyou.DTO.ClientDTO;
import com.youcode.gameyou.Entity.Client;
import com.youcode.gameyou.Exception.ApiException;
import com.youcode.gameyou.Mapper.Mapper;
import com.youcode.gameyou.Provider.RegisterProvider.interfaces.IRegisterAsClientProvider;
import com.youcode.gameyou.Repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegisterAsClientProvider implements IRegisterAsClientProvider {
    private final Mapper<ClientDTO, Client> mapper;
    private final ClientRepository clientRepository;
    @Override
    public void register(ClientDTO registerRequestDTO) {
        clientRepository.findByEmail(registerRequestDTO.getEmail())
                .orElseThrow(() -> new ApiException("Email already taken", HttpStatus.BAD_REQUEST));

        // map the DTO to the entity
        Client client = mapper.convertAtoB(registerRequestDTO, Client.class);
        // save the entity
        clientRepository.save(client);
    }
}