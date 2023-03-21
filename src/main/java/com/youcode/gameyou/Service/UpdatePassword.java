package com.youcode.gameyou.Service;

import com.youcode.gameyou.DTO.UserDTO;
import com.youcode.gameyou.Entity.UserParent;
import com.youcode.gameyou.Exception.ApiException;
import com.youcode.gameyou.Mapper.Mapper;
import com.youcode.gameyou.Provider.FindClientProvider;
import com.youcode.gameyou.Repository.UserParentRepository;
import com.youcode.gameyou.Request.UpdatePasswordRequest;
import com.youcode.gameyou.Service.Interfaces.IUpdatePassword;

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
    private final UserParentRepository userParentRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper<UserDTO, UserParent> mapper;
    private final FindClientProvider findClientProvider;
    @Override
    public UserDTO updatePassword(UpdatePasswordRequest updateRequest, String userType, Long id) {
        // find the user if exist
        var a = findClientProvider.findClient(id);

        Boolean checkIfPasswordIsCorrect = passwordEncoder.matches(updateRequest.getOldPassword(), a.getHashedPassword());

        if(checkIfPasswordIsCorrect) {
            // check if the new password and confirm new password are the same
            if(updateRequest.getNewPassword().equals(updateRequest.getConfirmNewPassword())) {
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(a, userDTO); // map UserParent to userDTO
                // update the password in userDTO
                userDTO.setHashedPassword(passwordEncoder.encode(updateRequest.getNewPassword()));
                // map userDTO to entity
                UserParent userParent = mapper.convertAtoB(userDTO, UserParent.class);
                userParentRepository.save(userParent); // save userParent
                return userDTO;
            } else {
                throw new ApiException("new password and confirm new password are not the same", HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new ApiException("old password is not correct", HttpStatus.BAD_REQUEST);
        }
    }
}