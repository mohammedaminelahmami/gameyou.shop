package com.youcode.gameyou.Factory;

import com.youcode.gameyou.Factory.Interfaces.ILoginFactory;
import com.youcode.gameyou.Provider.LoginFacebookProviderImpl;
import com.youcode.gameyou.Provider.LoginGameYouProviderImpl;
import com.youcode.gameyou.Provider.LoginGoogleProviderImpl;
import com.youcode.gameyou.Request.Auth.LoginRequest;
import com.youcode.gameyou.Response.Auth.LoginResponse;
import org.springframework.stereotype.Component;

@Component
public class LoginFactoryImpl implements ILoginFactory {
    @Override
    public LoginResponse getLoginFactory(LoginRequest loginRequest) {
        switch (loginRequest.getLoginProviderType()) {
            case "GAMEYOU" -> {
                return LoginGameYouProviderImpl.builder().build().loginWithGameYou(loginRequest);
            }
            case "GOOGLE" -> {
                return LoginGoogleProviderImpl.builder().build().loginWithGoogle();
            }
            case "FACEBOOK" -> {
                return LoginFacebookProviderImpl.builder().build().loginWithFacebook();
            }
            default -> throw new IllegalArgumentException("Invalid provider type: " + loginRequest.getLoginProviderType());
        }
    }
}