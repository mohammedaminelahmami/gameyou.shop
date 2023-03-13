package com.youcode.gameyou.Controller;

import com.youcode.gameyou.Request.Client.AddNewClientRequest;
import com.youcode.gameyou.Request.Client.UpdateClientInfoRequest;
import com.youcode.gameyou.Request.Client.UpdateClientPassword;
import com.youcode.gameyou.Response.Client.ClientResponse;
import com.youcode.gameyou.Service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {
    // TODO : CRUD Client ( delete , get , getAll )
    private final ClientService clientService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/updateInfo")
    public ClientResponse updatePassword(@RequestBody @Valid UpdateClientInfoRequest updateClientInfoRequest) {
        return clientService.updateInfo(updateClientInfoRequest);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/updatePassword")
    public void updatePassword(@RequestBody @Valid UpdateClientPassword updateClientPassword) {
        clientService.updatePassword(updateClientPassword);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        clientService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ClientResponse getOne(@PathVariable String id) {
        return clientService.getOne(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ClientResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return clientService.getAll(page, size);
    }
}