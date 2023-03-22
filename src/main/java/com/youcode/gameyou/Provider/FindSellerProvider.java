package com.youcode.gameyou.Provider;

import com.youcode.gameyou.Entity.Seller;
import com.youcode.gameyou.Exception.ApiException;
import com.youcode.gameyou.Factory.UserTypeRepositoryFactory;
import com.youcode.gameyou.Provider.Interfaces.IFindSellerProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindSellerProvider implements IFindSellerProvider {
    private final UserTypeRepositoryFactory userTypeRepositoryFactory;
    @Override
    public Seller findSeller(Long id) {
        Seller findSeller = (Seller) userTypeRepositoryFactory.getUserTypeRepository("seller").findById(id)
                .orElseThrow(() -> new ApiException("Seller not found", HttpStatus.BAD_REQUEST));
        return findSeller;
    }
}