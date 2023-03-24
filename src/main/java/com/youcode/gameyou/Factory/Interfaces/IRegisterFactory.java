package com.youcode.gameyou.Factory.Interfaces;

import com.youcode.gameyou.DTO.UserDTO;

public interface IRegisterFactory {
    void register(UserDTO userDTO, String roleType);
}
