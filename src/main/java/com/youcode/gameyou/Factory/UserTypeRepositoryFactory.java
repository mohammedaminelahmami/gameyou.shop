package com.youcode.gameyou.Factory;

import com.youcode.gameyou.Factory.Interfaces.IUserTypeRepositoryFactory;
import com.youcode.gameyou.Repository.AdminRepository;
import com.youcode.gameyou.Repository.ClientRepository;
import com.youcode.gameyou.Repository.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserTypeRepositoryFactory implements IUserTypeRepositoryFactory {
    private final AdminRepository adminRepository;
    private final SellerRepository sellerRepository;
    private final ClientRepository clientRepository;
    @Override
    public <T extends JpaRepository<?, Long>> T getUserTypeRepository(String userType) {
        switch (userType)
        {
            case "admin" -> {
                return (T) adminRepository;
            }
            case "seller" -> {
                return (T) sellerRepository;
            }
            case "client" -> {
                return (T) clientRepository;
            }
            default -> throw new IllegalArgumentException("Invalid user type");
        }
    }
}
