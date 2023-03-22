package com.youcode.gameyou.Factory.Interfaces;

import com.youcode.gameyou.Entity.UserParent;

public interface IGetNewInstanceOfUserFactory {
    <T extends UserParent> T getNewInstanceOfUser(String userType);
}
