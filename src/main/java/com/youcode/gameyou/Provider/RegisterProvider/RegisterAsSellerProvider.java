package com.youcode.gameyou.Provider.RegisterProvider;

import com.youcode.gameyou.DTO.SellerDTO;
import com.youcode.gameyou.Entity.Seller;
import com.youcode.gameyou.Exception.ApiException;
import com.youcode.gameyou.Mapper.Mapper;
import com.youcode.gameyou.Provider.RegisterProvider.interfaces.IRegisterAsSellerProvider;
import com.youcode.gameyou.Repository.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegisterAsSellerProvider implements IRegisterAsSellerProvider {
    private final Mapper<SellerDTO, Seller> mapper;
    private final SellerRepository sellerRepository;
    @Override
    public void register(SellerDTO registerRequestDTO) {
        sellerRepository.findByEmail(registerRequestDTO.getEmail())
                .orElseThrow(() -> new ApiException("Email already taken", HttpStatus.BAD_REQUEST));

        // map the DTO to the entity
        Seller seller = mapper.convertAtoB(registerRequestDTO, Seller.class);
        // save the entity
        sellerRepository.save(seller);
    }
}