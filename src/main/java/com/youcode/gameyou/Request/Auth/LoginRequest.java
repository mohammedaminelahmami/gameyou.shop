package com.youcode.gameyou.Request.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class LoginRequest {
    private String email;
    private String password;
    private String loginProviderType;
}