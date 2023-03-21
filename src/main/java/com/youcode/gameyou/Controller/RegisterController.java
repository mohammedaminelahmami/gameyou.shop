//package com.youcode.gameyou.Controller;
//
//import com.youcode.gameyou.Request.Client.AddNewClientRequest;
//import com.youcode.gameyou.Response.Client.ClientResponse;
//import com.youcode.gameyou.Service.ClientService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/register")
//@RequiredArgsConstructor
//public class RegisterController {
//    private final ClientService clientService;
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/client")
//    public ClientResponse save(@RequestBody @Valid AddNewClientRequest addNewClientRequest) {
//        return clientService.save(addNewClientRequest);
//    }
//}