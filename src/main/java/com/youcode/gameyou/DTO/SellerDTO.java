package com.youcode.gameyou.DTO;

import com.youcode.gameyou.Entity.Store;
import com.youcode.gameyou.Enum.Role;
import lombok.*;

import java.util.Date;

@Getter
@Setter
public class SellerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String hashedPassword;
    private String imagePath;
    private String address;
    private String phone;
    private String country;
    private String city;
    private String state;
    private String zipcode;
    private Role role;
    private Boolean isActive;
    private Date createdAt;
    private Date updatedAt;
    private Store store;
}