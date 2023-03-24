package com.youcode.gameyou.Provider.LoginProvider.interfaces;

import com.youcode.gameyou.DTO.UserDTO;

public interface ILoginAsSellerProvider {
    UserDTO login(UserDTO loginRequestDTO, String type);
}
