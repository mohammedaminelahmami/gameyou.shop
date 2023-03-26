package com.youcode.gameyou.Provider.FindUserProvider;

import com.youcode.gameyou.Entity.Admin;
import com.youcode.gameyou.Exception.ApiException;
import com.youcode.gameyou.Factory.UserTypeRepositoryFactory;
import com.youcode.gameyou.Provider.FindUserProvider.Interfaces.IFindAdminProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindAdminProvider implements IFindAdminProvider {
    private final UserTypeRepositoryFactory userTypeRepositoryFactory;
    @Override
    public Admin findAdmin(Long id) {
        Admin findAdmin = (Admin) userTypeRepositoryFactory.getUserTypeRepository("admin").findById(id)
                .orElseThrow(() -> new ApiException("Admin not found", HttpStatus.BAD_REQUEST));
        return findAdmin;
    }
}