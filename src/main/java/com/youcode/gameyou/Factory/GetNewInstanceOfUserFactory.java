package com.youcode.gameyou.Factory;

import com.youcode.gameyou.Entity.Admin;
import com.youcode.gameyou.Entity.Client;
import com.youcode.gameyou.Entity.Seller;
import com.youcode.gameyou.Entity.UserParent;
import com.youcode.gameyou.Factory.Interfaces.IGetNewInstanceOfUserFactory;
import org.springframework.stereotype.Component;

@Component
public class GetNewInstanceOfUserFactory implements IGetNewInstanceOfUserFactory {
    @Override
    public <T extends UserParent> T getNewInstanceOfUser(String userType) {
        switch (userType)
        {
            case "client"-> {
                return (T) new Client();
            }
            case "seller" -> {
                return (T) new Seller();
            }
            case "admin" -> {
                return (T) new Admin();
            }
            default -> throw new IllegalArgumentException("Invalid user type");
        }
    }
}