package com.youcode.gameyou.Service;

import com.youcode.gameyou.DTO.UserDTO;
import com.youcode.gameyou.Factory.RegisterFactory;
import com.youcode.gameyou.Security.config.JwtUtil;
import com.youcode.gameyou.Service.Interfaces.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @Override
    public ResponseEntity<HashMap<String, Object>> login(UserDTO loginRequestDTO) {
        HashMap<String, Object> response = new HashMap<>();
        UserDetails user = userService.loadUserByUsername(loginRequestDTO.getEmail());
        System.out.println(user.getAuthorities());
        if(user != null) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getEmail(),
                            loginRequestDTO.getHashedPassword())
            );
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
}