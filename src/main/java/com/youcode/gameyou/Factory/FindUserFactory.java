package com.youcode.gameyou.Factory;

import com.youcode.gameyou.Entity.UserParent;
import com.youcode.gameyou.Factory.Interfaces.IFindUserFactory;
import com.youcode.gameyou.Provider.FindAdminProvider;
import com.youcode.gameyou.Provider.FindClientProvider;
import com.youcode.gameyou.Provider.FindSellerProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindUserFactory implements IFindUserFactory {
    private final FindClientProvider findClientProvider;
    private final FindSellerProvider findSellerProvider;
    private final FindAdminProvider findAdminProvider;
    @Override
    public <T extends UserParent> T findUser(String userType, Long id) {
        switch (userType)
        {
            case "client"-> {
                return (T) findClientProvider.findClient(id);
            }
            case "seller" -> {
                return (T) findSellerProvider.findSeller(id);
            }
            case "admin" -> {
                return (T) findAdminProvider.findAdmin(id);
            }
            default -> throw new IllegalArgumentException("Invalid user type");
        }
    }
}
