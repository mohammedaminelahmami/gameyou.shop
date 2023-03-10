package com.youcode.gameyou.Controller;

import com.youcode.gameyou.Request.Auth.LoginRequest;
import com.youcode.gameyou.Request.Auth.RegisterRequest;
import com.youcode.gameyou.Response.Auth.LoginResponse;
import com.youcode.gameyou.Service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public LoginResponse login (@RequestBody @Valid LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void register (@RequestBody @Valid RegisterRequest registerRequest) {
        authService.register(registerRequest);
    }
}