package com.youcode.gameyou.Service;

import com.youcode.gameyou.DTO.UserDTO;
import com.youcode.gameyou.Entity.Admin;
import com.youcode.gameyou.Entity.Client;
import com.youcode.gameyou.Entity.Seller;
import com.youcode.gameyou.Exception.ApiException;
import com.youcode.gameyou.Factory.FindUserFactory;
import com.youcode.gameyou.Factory.GetNewInstanceOfUserFactory;
import com.youcode.gameyou.Factory.UserTypeRepositoryFactory;
import com.youcode.gameyou.Repository.AdminRepository;
import com.youcode.gameyou.Repository.ClientRepository;
import com.youcode.gameyou.Repository.SellerRepository;
import com.youcode.gameyou.Request.UpdatePasswordRequest;
import com.youcode.gameyou.Service.Interfaces.IUpdatePassword;

import com.youcode.gameyou.Util.JavaObjectUtil;
import jakarta.transaction.Transactional;

import lombok.AllArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class UpdatePassword implements IUpdatePassword {
    private final PasswordEncoder passwordEncoder;
    private final FindUserFactory findUserFactory;
    private final UserTypeRepositoryFactory userTypeRepositoryFactory;
    private final GetNewInstanceOfUserFactory getNewInstanceOfUserFactory;
    @Override
    public UserDTO updatePassword(UpdatePasswordRequest updateRequest, String userType, Long id) {
        // find the user if exist
        var findUser = findUserFactory.findUser(userType ,id);
        Boolean checkIfPasswordIsCorrect = passwordEncoder.matches(updateRequest.getOldPassword(), findUser.getHashedPassword());

        if(checkIfPasswordIsCorrect) {
            // check if the new password and confirm new password are the same
            if(updateRequest.getNewPassword().equals(updateRequest.getConfirmNewPassword())) {
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(findUser, userDTO); // map User ? to userDTO
                // update the password in userDTO
                userDTO.setHashedPassword(passwordEncoder.encode(updateRequest.getNewPassword()));
                // map userDTO to User ?
                var user = getNewInstanceOfUserFactory.getNewInstanceOfUser(userType); // get new instance of user <Admin or Seller or Client>
                BeanUtils.copyProperties(userDTO, user);
                if(userType.equals("admin")) {
                    var a = (AdminRepository) userTypeRepositoryFactory.getUserTypeRepository("admin"); // save admin in DB
                    a.save((Admin) user);
                }else if(userType.equals("client")) {
                    var a = (ClientRepository) userTypeRepositoryFactory.getUserTypeRepository("client"); // save client in DB
                    a.save((Client) user);
                }else if(userType.equals("seller")) {
                    var a = (SellerRepository) userTypeRepositoryFactory.getUserTypeRepository("seller"); // save seller in DB
                    a.save((Seller) user);
                }else {
                    throw new ApiException("user type is not correct", HttpStatus.BAD_REQUEST);
                }
                return userDTO;
            } else {
                throw new ApiException("new password and confirm new password are not the same", HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new ApiException("old password is not correct", HttpStatus.BAD_REQUEST);
        }
    }
}