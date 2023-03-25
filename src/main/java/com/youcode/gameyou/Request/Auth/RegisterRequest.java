package com.youcode.gameyou.Request.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message = "firstName is required")
    private String firstName;

    @NotBlank(message = "lastName is required")
    private String lastName;

    @NotBlank(message = "email is required")
    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "confirmPassword is required")
    private String confirmPassword;
}