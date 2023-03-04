package com.youcode.gameyou.Service;

import com.youcode.gameyou.Request.Auth.LoginRequest;
import com.youcode.gameyou.Request.Auth.RegisterRequest;
import com.youcode.gameyou.Response.Auth.LoginResponse;
import com.youcode.gameyou.Service.interfaces.IAuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public void register(RegisterRequest registerRequest) {

    }
}
