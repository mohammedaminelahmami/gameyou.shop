package com.youcode.gameyou.Factory.Interfaces;

import com.youcode.gameyou.Entity.UserParent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserTypeRepositoryFactory {
    <T extends JpaRepository<? extends UserParent, Long>> T getUserTypeRepository(String userType);
}
