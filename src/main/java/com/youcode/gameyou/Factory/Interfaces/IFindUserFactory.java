package com.youcode.gameyou.Factory.Interfaces;

import com.youcode.gameyou.Entity.UserParent;

public interface IFindUserFactory {
    <T extends UserParent> T findUser(String userType, Long id);
}