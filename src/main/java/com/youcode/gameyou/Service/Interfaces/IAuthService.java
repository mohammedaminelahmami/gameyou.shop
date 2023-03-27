package com.youcode.gameyou.Service.Interfaces;

import com.youcode.gameyou.DTO.UserDTO;
import com.youcode.gameyou.Entity.Admin;
import com.youcode.gameyou.Entity.Client;
import com.youcode.gameyou.Entity.Seller;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public interface IAuthService {
    ResponseEntity<HashMap<String, Object>> login(UserDTO loginRequestDTO);
    void register(UserDTO RegisterRequestDTO, String roleType);
    Client getAuthenticatedClient();
    Seller getAuthenticatedSeller();
    Admin getAuthenticatedAdmin();
}