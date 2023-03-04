package com.youcode.gameyou.Service.interfaces;

import com.youcode.gameyou.Request.Auth.LoginRequest;
import com.youcode.gameyou.Request.Auth.RegisterRequest;
import com.youcode.gameyou.Response.Auth.LoginResponse;

public interface IAuthService {
    LoginResponse login(LoginRequest loginRequest);
    void register(RegisterRequest registerRequest);
}