package com.youcode.gameyou.Provider.RegisterProvider;

import com.youcode.gameyou.DTO.SellerDTO;
import com.youcode.gameyou.Entity.Seller;
import com.youcode.gameyou.Entity.Store;
import com.youcode.gameyou.Enum.Role;
import com.youcode.gameyou.Exception.ApiException;
import com.youcode.gameyou.Mapper.Mapper;
import com.youcode.gameyou.Provider.RegisterProvider.interfaces.IRegisterAsSellerProvider;
import com.youcode.gameyou.Repository.SellerRepository;
import com.youcode.gameyou.Repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class RegisterAsSellerProvider implements IRegisterAsSellerProvider {
    private final Mapper<SellerDTO, Seller> mapper;
    private final SellerRepository sellerRepository;
    private final StoreRepository storeRepository;
    @Override
    public void register(SellerDTO registerRequestDTO) {
        try {
            if(sellerRepository.findByEmail(registerRequestDTO.getEmail()).isPresent()) throw new ApiException("Email already taken", HttpStatus.BAD_REQUEST);
            registerRequestDTO.setIsActive(true);
            registerRequestDTO.setRole(Role.ROLE_SELLER);
            registerRequestDTO.setCreatedAt(new Date());
            registerRequestDTO.setUpdatedAt(new Date());
            // map the DTO to the entity
            Seller seller = mapper.convertAtoB(registerRequestDTO, Seller.class);
            // save the entity
            sellerRepository.save(seller);

            // create a store for the seller
            Store store = new Store();
            store.setIsActive(true);
            storeRepository.save(store);
            // set the store to the seller
            seller.setStore(store);
            sellerRepository.save(seller);
        } catch (Exception e) {
            throw new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}