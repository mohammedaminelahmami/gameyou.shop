package com.youcode.gameyou.Controller;

import com.youcode.gameyou.DTO.UserDTO;
import com.youcode.gameyou.Exception.ApiException;
import com.youcode.gameyou.Request.Auth.LoginRequest;
import com.youcode.gameyou.Request.Auth.RegisterRequest;
import com.youcode.gameyou.Service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/api/authentication")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public ResponseEntity<HashMap<String, Object>> login(@RequestBody @Valid LoginRequest loginRequest) {
        // map loginRequest to DTO
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(loginRequest, userDTO);
        return authService.login(userDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register/{roleType}")
    public void register (@RequestBody @Valid RegisterRequest registerRequest, @PathVariable String roleType) {
        if(!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) throw new ApiException("password and confirmPassword are not same", HttpStatus.BAD_REQUEST);
        // map RegisterRequest to UserDTO
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(registerRequest, userDTO);
        userDTO.setHashedPassword(passwordEncoder.encode(registerRequest.getPassword()));
        authService.register(userDTO, roleType);
    }
}