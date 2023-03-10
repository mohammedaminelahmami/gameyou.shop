package com.youcode.gameyou.Provider.Interfaces;

import com.youcode.gameyou.Request.Auth.LoginRequest;
import com.youcode.gameyou.Response.Auth.LoginResponse;

public interface ILoginGameYouProvider {
    LoginResponse loginWithGameYou(LoginRequest loginRequest);
}