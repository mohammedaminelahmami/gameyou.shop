package com.youcode.gameyou.Provider;

import com.youcode.gameyou.Provider.Interfaces.ILoginGameYouProvider;
import com.youcode.gameyou.Request.Auth.LoginRequest;
import com.youcode.gameyou.Response.Auth.LoginResponse;

import com.youcode.gameyou.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Map;

@Builder
@AllArgsConstructor
public class LoginGameYouProviderImpl implements ILoginGameYouProvider {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    @Override
    public LoginResponse loginWithGameYou(LoginRequest loginRequest) {
        if (authenticationManager != null) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            return LoginResponse.builder().response(Map.of("result", authentication.getAuthorities())).build();
        } else {
            throw new IllegalStateException("authenticationManager is null");
        }
    }
}