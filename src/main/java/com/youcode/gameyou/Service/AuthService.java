package com.youcode.gameyou.Service;

import com.youcode.gameyou.DTO.UserDTO;
import com.youcode.gameyou.Factory.RegisterFactory;
import com.youcode.gameyou.Service.Interfaces.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {
    private final RegisterFactory registerFactory;
    @Override
    public UserDTO login(UserDTO loginRequestDTO, String roleType) {
//        registerFactory.login(loginRequestDTO, roleType);
        return null;
    }

    @Override
    public void register(UserDTO RegisterRequestDTO, String roleType) {
        registerFactory.register(RegisterRequestDTO, roleType);
    }
}