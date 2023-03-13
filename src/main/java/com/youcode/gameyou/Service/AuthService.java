//package com.youcode.gameyou.Service;
//
//import com.youcode.gameyou.Factory.LoginFactoryImpl;
//import com.youcode.gameyou.Request.Auth.LoginRequest;
//import com.youcode.gameyou.Request.Auth.RegisterRequest;
//import com.youcode.gameyou.Response.Auth.LoginResponse;
//import com.youcode.gameyou.Service.Interfaces.IAuthService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class AuthService implements IAuthService {
//    private final LoginFactoryImpl loginFactoryImpl;
//    @Override
//    public LoginResponse login(LoginRequest loginRequest) {
//        return loginFactoryImpl.getLoginFactory(loginRequest);
//    }
//
//    @Override
//    public void register(RegisterRequest registerRequest) {
//
//    }
//
//}