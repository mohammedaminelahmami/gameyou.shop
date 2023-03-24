package com.youcode.gameyou.Factory.Interfaces;

import com.youcode.gameyou.DTO.UserDTO;
import com.youcode.gameyou.Response.Auth.AuthResponse;

public interface ILoginFactory {
    AuthResponse login(UserDTO userDTO, String roleType);
}
