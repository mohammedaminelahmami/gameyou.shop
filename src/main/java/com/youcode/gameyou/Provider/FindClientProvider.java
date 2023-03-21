package com.youcode.gameyou.Provider;

import com.youcode.gameyou.Entity.Client;
import com.youcode.gameyou.Exception.ApiException;
import com.youcode.gameyou.Factory.UserTypeRepositoryFactory;
import com.youcode.gameyou.Provider.Interfaces.IFindClientProvider;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindClientProvider implements IFindClientProvider {
    private final UserTypeRepositoryFactory userTypeRepositoryFactory;
    @Override
    public Client findClient(Long id) {
        Client findClient = (Client) userTypeRepositoryFactory.getUserTypeRepository("client").findById(id)
                .orElseThrow(() -> new ApiException("User not found", HttpStatus.BAD_REQUEST));
        return findClient;
    }
}