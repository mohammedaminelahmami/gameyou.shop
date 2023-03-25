package com.youcode.gameyou.Provider.LoginProvider.interfaces;

import com.youcode.gameyou.DTO.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public interface ILoginAsAdminProvider {
    ResponseEntity<HashMap<String, Object>> login(UserDTO loginRequestDTO);
}
