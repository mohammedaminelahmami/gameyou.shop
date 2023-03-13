package com.youcode.gameyou.DTO;

import com.youcode.gameyou.Enum.Role;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String hashedpassword;
    private String imagepath;
    private String address;
    private String phone;
    private String country;
    private String city;
    private String state;
    private String zipcode;
    private Role role;
}