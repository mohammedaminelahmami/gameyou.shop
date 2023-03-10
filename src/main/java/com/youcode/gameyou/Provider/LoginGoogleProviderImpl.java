package com.youcode.gameyou.Provider;

import com.youcode.gameyou.Provider.Interfaces.ILoginGoogleProvider;
import com.youcode.gameyou.Response.Auth.LoginResponse;
import lombok.Builder;

@Builder
public class LoginGoogleProviderImpl implements ILoginGoogleProvider {

    @Override
    public LoginResponse loginWithGoogle() {
        return null;
    }
}