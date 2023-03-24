package com.youcode.gameyou.Request.Seller;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UpdateSellerInfoRequest {
    private String firstName;
    private String lastName;
    private String email;

    @NotBlank(message = "password is required")
    private String hashedPassword;
}