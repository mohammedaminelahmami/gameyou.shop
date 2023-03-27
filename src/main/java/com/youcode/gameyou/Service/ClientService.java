package com.youcode.gameyou.Service;

import com.youcode.gameyou.DTO.ClientDTO;
import com.youcode.gameyou.Entity.Client;
import com.youcode.gameyou.Exception.ApiException;
import com.youcode.gameyou.Mapper.Mapper;
import com.youcode.gameyou.Repository.ClientRepository;
import com.youcode.gameyou.Service.Interfaces.IClientService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ClientService implements IClientService {
    private final UploadFileService uploadFileService;
    private final AuthService authService;
    private final ClientRepository clientRepository;
    private final Mapper<ClientDTO , Client> mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ClientDTO save(ClientDTO addNewClientDTO) {
        // check if email is already exist or throw exception
        clientRepository.findByEmail(addNewClientDTO.getEmail())
                        .orElseThrow(() -> new ApiException("Client already exist", HttpStatus.BAD_REQUEST));
        Client client = mapper.convertAtoB(addNewClientDTO, Client.class); // map clientDTO to client
        clientRepository.save(client); // save client
        ClientDTO clientDTO = mapper.convertBtoA(client, ClientDTO.class); // map client to clientDTO
        return clientDTO;
    }

    @Override
    public ClientDTO updateInfo(ClientDTO updateClientInfoDTO, Long id) {
        // find the client
        Client findClientById = clientRepository.findById(id).orElseThrow(() -> new ApiException("Client not found", HttpStatus.BAD_REQUEST));
        ClientDTO clientDTO = mapper.convertBtoA(findClientById, ClientDTO.class); // map client to clientDTO

        // check if the password match the password in the database
        Boolean checkIsPasswordMatch = passwordEncoder.matches(updateClientInfoDTO.getHashedPassword(), clientDTO.getHashedPassword());

        if(!checkIsPasswordMatch) throw new RuntimeException("password not match");

        if(updateClientInfoDTO.getFirstName() != null) clientDTO.setFirstName(updateClientInfoDTO.getFirstName());
        if(updateClientInfoDTO.getLastName() != null) clientDTO.setLastName(updateClientInfoDTO.getLastName());
        if(updateClientInfoDTO.getEmail() != null) clientDTO.setEmail(updateClientInfoDTO.getEmail());

        // map clientDTO to client
        Client client = mapper.convertAtoB(clientDTO, Client.class);
        clientRepository.save(client); // save client

        return clientDTO;
    }

    @Override
    public void delete(Long id) {
        // find the client and update isActive to false
        Client findClient = clientRepository.findById(id).orElseThrow(() -> new ApiException("Client not found", HttpStatus.BAD_REQUEST));
        // map client to clientDTO
        ClientDTO clientDTO = mapper.convertBtoA(findClient, ClientDTO.class);
        clientDTO.setIsActive(false);
        // map clientDTO to client
        Client client = mapper.convertAtoB(clientDTO, Client.class);
        clientRepository.save(client);
    }

    @Override
    public ClientDTO getOne(Long id) {
        // find the client
        Client client = clientRepository.findById(id).orElseThrow(() -> new ApiException("Client not found", HttpStatus.BAD_REQUEST));
        ClientDTO clientDTO = mapper.convertBtoA(client, ClientDTO.class);
        return clientDTO;
    }

    @Override
    public List<ClientDTO> getAll(int page, int size) {
        if(page > 0) page--;
        List<Client> clients = clientRepository.findAll(PageRequest.of(page, size)).stream().toList();
        // map clients to clientDTOS
        List<ClientDTO> clientDTOS = mapper.convertListBToListA(clients, ClientDTO.class);
        return clientDTOS;
    }

    @Override
    public String uploadImage (MultipartFile imageFile) {
        if(imageFile == null) throw new RuntimeException("image is null");

        Client client = authService.getAuthenticatedClient();
        if (client == null) throw new RuntimeException("client not found");

        String path = uploadFileService.getOnePath(imageFile);
        if (path == null || path.equals("")) throw new RuntimeException("File not found");

        client.setImagePath(path);
        clientRepository.save(client);
        return path;
    }
}