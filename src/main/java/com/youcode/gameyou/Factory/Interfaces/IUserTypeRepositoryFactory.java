package com.youcode.gameyou.Factory.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserTypeRepositoryFactory {
    <T extends JpaRepository<?, Long>> T getUserTypeRepository(String userType);
}
