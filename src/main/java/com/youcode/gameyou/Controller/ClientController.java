package com.youcode.gameyou.Controller;

import com.youcode.gameyou.DTO.ClientDTO;
import com.youcode.gameyou.DTO.UserDTO;
import com.youcode.gameyou.Mapper.Mapper;
import com.youcode.gameyou.Request.Client.UpdateClientInfoRequest;
import com.youcode.gameyou.Request.UpdatePasswordRequest;
import com.youcode.gameyou.Response.Client.ClientResponse;
import com.youcode.gameyou.Service.ClientService;
import com.youcode.gameyou.Service.UpdatePassword;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final UpdatePassword updatePassword;
    private final Mapper<ClientDTO, ClientResponse> mapper;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/updateInfo/{id}")
    public ClientResponse updateInfo (@RequestBody @Valid UpdateClientInfoRequest updateClientInfoRequest, @PathVariable Long id) {
        // map updateClientInfoRequest to ClientDTO
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(updateClientInfoRequest, clientDTO);
        ClientDTO clientDTOAfterUpdate = clientService.updateInfo(clientDTO, id);
        // map clientDTO to clientResponse
        ClientResponse clientResponse = mapper.convertAtoB(clientDTO, ClientResponse.class);
        return clientResponse;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/updatePassword/{id}")
    public ClientResponse updatePassword (@RequestBody @Valid UpdatePasswordRequest updateClientPassword, @PathVariable Long id) {
        // map updateClientPassword to clientDTO
        UserDTO userDTO = updatePassword.updatePassword(updateClientPassword, "client", id);
        // map userDTO to clientDTO
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(userDTO, clientDTO);
        ClientResponse clientResponse = mapper.convertAtoB(clientDTO, ClientResponse.class);
        return clientResponse;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/updateImageProfile")
    public String updateImageProfile(@RequestParam MultipartFile imageFile) {
        // update image profile and return the path of the image
        return clientService.uploadImage(imageFile);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        clientService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ClientResponse getOne (@PathVariable Long id) {
        ClientDTO clientDTO = clientService.getOne(id);
        // map clientDTO to clientResponse
        ClientResponse clientResponse = mapper.convertAtoB(clientDTO, ClientResponse.class);
        return clientResponse;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ClientResponse> getAll (@RequestParam int page, @RequestParam int size) {
        List<ClientDTO> clientDTOS = clientService.getAll(page, size);
        // map clientDTOS to clientResponses
        List<ClientResponse> clientResponses = mapper.convertListAToListB(clientDTOS, ClientResponse.class);
        return clientResponses;
    }
}