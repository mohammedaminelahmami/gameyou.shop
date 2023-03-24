package com.youcode.gameyou.Provider.LoginProvider;

import com.youcode.gameyou.DTO.UserDTO;
import com.youcode.gameyou.Provider.LoginProvider.interfaces.ILoginAsSellerProvider;
import org.springframework.stereotype.Component;

@Component
public class LoginAsSellerProvider implements ILoginAsSellerProvider {
    @Override
    public UserDTO login(UserDTO loginRequestDTO, String type) {
        return null;
    }
}
