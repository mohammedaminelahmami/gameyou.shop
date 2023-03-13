package com.youcode.gameyou.Service;

import com.youcode.gameyou.DTO.Mapper.IMapperDTO;
import com.youcode.gameyou.DTO.UserDTO;
import com.youcode.gameyou.Entity.UserParent;
import com.youcode.gameyou.Repository.UserParentRepository;
import com.youcode.gameyou.Request.Client.AddNewClientRequest;
import com.youcode.gameyou.Request.Client.UpdateClientInfoRequest;
import com.youcode.gameyou.Request.Client.UpdateClientPassword;
import com.youcode.gameyou.Response.Client.ClientResponse;
import com.youcode.gameyou.Service.Interfaces.IClientService;
import com.youcode.gameyou.Util.KeycloakUtils;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ClientService implements IClientService {
    private final UserParentRepository userParentRepository;
    private final IMapperDTO<UserDTO, UserParent> mapper;
    private final IMapperDTO<ClientResponse, UserDTO> mapResponse;
    private final PasswordEncoder passwordEncoder;
    private final KeycloakUtils keycloakUtils;
    @Override
    public ClientResponse save(AddNewClientRequest addNewClientRequest) {
        Boolean checkClientIfAlreadyExist = userParentRepository.findByEmail(addNewClientRequest.getEmail()).isPresent();
        if(checkClientIfAlreadyExist) throw new RuntimeException("Client already exist");
        // check if password and confirmPassword are the same
        if(!addNewClientRequest.getPassword().equals(addNewClientRequest.getConfirmPassword())) throw new RuntimeException("Password and ConfirmPassword are not the same");
        // map request to DTO
        UserDTO userDTO = UserDTO.builder()
                .firstName(addNewClientRequest.getFirstName())
                .lastName(addNewClientRequest.getLastName())
                .email(addNewClientRequest.getEmail())
                .hashedpassword(passwordEncoder.encode(addNewClientRequest.getPassword())).build();
        // map DTO to Entity & save to Keycloak
        UserParent userParent = new UserParent();
        BeanUtils.copyProperties(userDTO, userParent);
//        userParent.setId(Long.parseLong(keycloakUtils.createKeycloakUserWithRole(userDTO)));
        keycloakUtils.createKeycloakUserWithRole(userDTO);
        // save to DB
        userParentRepository.save(userParent);
        // map DTO to Response
        ClientResponse clientResponse = ClientResponse.builder()
                                        .firstName(userDTO.getFirstName())
                                        .lastName(userDTO.getLastName())
                                        .email(userDTO.getEmail())
                                        .build();
        return clientResponse;
    }

    @Override
    public ClientResponse getOne(String id) {
        return null;
    }

    @Override
    public List<ClientResponse> getAll(int page, int size) {
        return null;
    }

    @Override
    public ClientResponse updateInfo(UpdateClientInfoRequest updateClientInfoRequest) {
        return null;
    }

    @Override
    public void updatePassword(UpdateClientPassword updateClientPassword) {

    }

    @Override
    public void delete(String id) {

    }
}
