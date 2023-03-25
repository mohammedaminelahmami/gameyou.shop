//package com.youcode.gameyou.Provider.LoginProvider;
//
//import com.youcode.gameyou.DTO.UserDTO;
//import com.youcode.gameyou.Provider.LoginProvider.interfaces.ILoginAsAdminProvider;
//import com.youcode.gameyou.Response.Auth.AuthResponse;
//import com.youcode.gameyou.Security.config.JwtUtil;
//import com.youcode.gameyou.Service.UserService;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.BeanUtils;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//
//@Component
//@AllArgsConstructor
//public class LoginProvider implements ILoginAsAdminProvider {
//    private final UserService userService;
//    private final JwtUtil jwtUtil;
//    private final AuthenticationManager authenticationManager;
//    @Override
//    public ResponseEntity<HashMap<String, Object>> login(UserDTO loginRequestDTO) {
//        HashMap<String, Object> response = new HashMap<>();
//        UserDetails user = userService.loadUserByUsername(loginRequestDTO.getEmail());
//
//        if(user != null) {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginRequestDTO.getEmail(),
//                            loginRequestDTO.getHashedPassword())
//                    );
//            // map UserDetails to UserDTO
//            UserDTO userDTO = new UserDTO();
//            BeanUtils.copyProperties(user, userDTO);
//            // map userDTO to authResponse
//            AuthResponse authResponse = new AuthResponse();
//            BeanUtils.copyProperties(userDTO, authResponse);
//
//            response.put("accessToken", jwtUtil.generateToken(user));
//            response.put("user", authResponse);
//
//            return ResponseEntity.ok(response);
//        }
//        response.put("accessToken", null);
//        response.put("user", null);
//        return ResponseEntity.badRequest().body(response);
//    }
//}