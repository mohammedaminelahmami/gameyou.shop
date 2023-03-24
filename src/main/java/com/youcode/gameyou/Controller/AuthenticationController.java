package com.youcode.gameyou.Controller;

import com.youcode.gameyou.DTO.UserDTO;
import com.youcode.gameyou.Request.Auth.LoginRequest;
import com.youcode.gameyou.Request.Auth.RegisterRequest;
import com.youcode.gameyou.Response.Auth.AuthResponse;
import com.youcode.gameyou.Service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// TODO : register ( {admin , seller, client} )
// TODO : login ( {admin , seller, client} )

@RestController
@RequestMapping("/api/authentication")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthService authService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest loginRequest) {
//        return authService.login();
        return null;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register/{roleType}")
    public void register (@RequestBody @Valid RegisterRequest registerRequest, @PathVariable String roleType) {
        // map RegisterRequest to UserDTO
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(registerRequest, userDTO);
        authService.register(userDTO, roleType);
    }
}