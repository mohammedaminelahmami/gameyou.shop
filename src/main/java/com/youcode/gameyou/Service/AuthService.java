package com.youcode.gameyou.Service;

import com.youcode.gameyou.DTO.UserDTO;
import com.youcode.gameyou.Entity.Admin;
import com.youcode.gameyou.Entity.Client;
import com.youcode.gameyou.Entity.Seller;
import com.youcode.gameyou.Factory.RegisterFactory;
import com.youcode.gameyou.Repository.AdminRepository;
import com.youcode.gameyou.Repository.ClientRepository;
import com.youcode.gameyou.Repository.SellerRepository;
import com.youcode.gameyou.Security.config.JwtUtil;
import com.youcode.gameyou.Service.Interfaces.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {
    private final RegisterFactory registerFactory;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final ClientRepository clientRepository;
    private final SellerRepository sellerRepository;
    private final AdminRepository adminRepository;

    @Override
    public ResponseEntity<HashMap<String, Object>> login(UserDTO loginRequestDTO) {
        HashMap<String, Object> response = new HashMap<>();
        UserDetails user = userService.loadUserByUsername(loginRequestDTO.getEmail());

        if(user != null) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getEmail(),
                            loginRequestDTO.getHashedPassword())
            );
            // Set the authentication in the Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            response.put("accessToken", jwtUtil.generateToken(user));
            return ResponseEntity.ok(response);
        }
        response.put("accessToken", null);
        return ResponseEntity.badRequest().body(response);
    }

    @Override
    public void register(UserDTO RegisterRequestDTO, String roleType) {
        registerFactory.register(RegisterRequestDTO, roleType);
    }

    public Client getAuthenticatedClient() {
        return clientRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
    }

    public Seller getAuthenticatedSeller() {
        return sellerRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
    }

    public Admin getAuthenticatedAdmin() {
        return adminRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
    }
}