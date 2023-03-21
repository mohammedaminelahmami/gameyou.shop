package com.youcode.gameyou.Service.Interfaces;

import com.youcode.gameyou.DTO.UserDTO;
import com.youcode.gameyou.Request.UpdatePasswordRequest;

public interface IUpdatePassword {
    UserDTO updatePassword(UpdatePasswordRequest updatePasswordRequest, String userType, Long id);
}