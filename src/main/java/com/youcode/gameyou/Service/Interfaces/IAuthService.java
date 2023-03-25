package com.youcode.gameyou.Service.Interfaces;

import com.youcode.gameyou.DTO.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public interface IAuthService {
    ResponseEntity<HashMap<String, Object>> login(UserDTO loginRequestDTO);
    void register(UserDTO RegisterRequestDTO, String roleType);
}