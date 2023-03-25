//package com.youcode.gameyou.Factory;
//
//import com.youcode.gameyou.DTO.UserDTO;
//import com.youcode.gameyou.Factory.Interfaces.ILoginFactory;
//import com.youcode.gameyou.Mapper.Mapper;
//import com.youcode.gameyou.Provider.LoginProvider.LoginAsAdminProvider;
//import com.youcode.gameyou.Provider.LoginProvider.LoginAsClientProvider;
//import com.youcode.gameyou.Provider.LoginProvider.LoginAsSellerProvider;
//import com.youcode.gameyou.Response.Auth.AuthResponse;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@Component
//@AllArgsConstructor
//public class LoginFactory implements ILoginFactory {
//    private final Mapper<UserDTO, AuthResponse> mapper;
//    private final LoginAsClientProvider loginAsClientProvider;
//    private final LoginAsSellerProvider loginAsSellerProvider;
//    private final LoginAsAdminProvider loginAsAdminProvider;
//    @Override
//    public AuthResponse login(UserDTO userDTO, String roleType) {
//        switch(roleType) {
//            case "admin" -> {
//                UserDTO getUserDTO = loginAsAdminProvider.login(userDTO, roleType);
//                AuthResponse authResponse = mapper.convertAtoB(getUserDTO, AuthResponse.class);
//                return authResponse;
//            }
//            case "seller" -> {
//                UserDTO getUserDTO = loginAsSellerProvider.login(userDTO, roleType);
//                AuthResponse authResponse = mapper.convertAtoB(getUserDTO, AuthResponse.class);
//                return authResponse;
//            }
//            case "client" -> {
//                UserDTO getUserDTO = loginAsClientProvider.login(userDTO, roleType);
//                AuthResponse authResponse = mapper.convertAtoB(getUserDTO, AuthResponse.class);
//                return authResponse;
//            }
//            default -> throw new IllegalStateException("Unexpected value: " + roleType);
//        }
//    }
//}