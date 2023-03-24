package com.youcode.gameyou.Provider.LoginProvider;

import com.youcode.gameyou.DTO.AdminDTO;
import com.youcode.gameyou.DTO.UserDTO;
import com.youcode.gameyou.Entity.Admin;
import com.youcode.gameyou.Exception.ApiException;
import com.youcode.gameyou.Mapper.Mapper;
import com.youcode.gameyou.Provider.FindUser.FindAdminProvider;
import com.youcode.gameyou.Provider.LoginProvider.interfaces.ILoginAsAdminProvider;
import com.youcode.gameyou.Repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LoginAsAdminProvider implements ILoginAsAdminProvider {
    private final Mapper<AdminDTO, Admin> mapper;
    private final AdminRepository adminRepository;
    @Override
    public UserDTO login(UserDTO loginRequestDTO, String type) {

        return null;
    }
}