package com.youcode.gameyou.Service.Interfaces;

import com.youcode.gameyou.DTO.UserDTO;

public interface IAuthService {
    UserDTO login(UserDTO loginRequestDTO, String roleType);
    void register(UserDTO RegisterRequestDTO, String roleType);
}