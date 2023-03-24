package com.youcode.gameyou.Factory;

import com.youcode.gameyou.DTO.ClientDTO;
import com.youcode.gameyou.DTO.SellerDTO;
import com.youcode.gameyou.DTO.UserDTO;
import com.youcode.gameyou.Factory.Interfaces.IRegisterFactory;
import com.youcode.gameyou.Provider.RegisterProvider.RegisterAsClientProvider;
import com.youcode.gameyou.Provider.RegisterProvider.RegisterAsSellerProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegisterFactory implements IRegisterFactory {
    private final RegisterAsClientProvider registerAsClientProvider;
    private final RegisterAsSellerProvider registerAsSellerProvider;

    @Override
    public void register(UserDTO userDTO, String roleType) {
        switch(roleType) {
            case "client" -> {
                // map userDTO to clientDTO
                ClientDTO clientDTO = new ClientDTO();
                BeanUtils.copyProperties(userDTO, clientDTO);
                registerAsClientProvider.register(clientDTO);
            }
            case "seller" -> {
                // map userDTO to sellerDTO
                SellerDTO sellerDTO = new SellerDTO();
                BeanUtils.copyProperties(userDTO, sellerDTO);
                registerAsSellerProvider.register(sellerDTO);
            }
            default -> throw new IllegalStateException("Unexpected value: " + roleType);
        }
    }
}
