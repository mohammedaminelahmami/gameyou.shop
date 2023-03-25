package com.youcode.gameyou.Repository;

import com.youcode.gameyou.Entity.UserParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserParentRepository extends JpaRepository<UserParent, Long> {
    UserParent findByEmail(String email);
}