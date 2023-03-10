package com.youcode.gameyou.Util;

import com.youcode.gameyou.Entity.Admin;
import com.youcode.gameyou.Entity.Client;
import com.youcode.gameyou.Entity.Seller;
import com.youcode.gameyou.Repository.AdminRepository;
import com.youcode.gameyou.Repository.ClientRepository;
import com.youcode.gameyou.Repository.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class FirstTimeInitializr implements CommandLineRunner {
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final SellerRepository sellerRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        // creates some users :

        if(false) {
            // create Admin :
            try {
                Admin admin = new Admin();
                admin.setEmail("admin@gmail.com");
                admin.setHashedpassword(passwordEncoder.encode("test2009"));

                adminRepository.save(admin);
            }catch (Exception e){
                System.out.println("Admin already exists");
            }

            // create Seller :
            try {
                Seller seller = new Seller();
                seller.setEmail("seller@gmail.com");
                seller.setHashedpassword(passwordEncoder.encode("seller0029+0"));

                sellerRepository.save(seller);
            }catch (Exception e){
                System.out.println("Seller already exists");
            }

            // create Client :
            try {
                Client client = new Client();
                client.setEmail("client@gmail.com");
                client.setHashedpassword(passwordEncoder.encode("client0029+0"));

                clientRepository.save(client);
            }catch (Exception e){
                System.out.println("Client already exists");
            }
        }

        System.out.println("Starting...");
    }
}
