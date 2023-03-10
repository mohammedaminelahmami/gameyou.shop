package com.youcode.gameyou.Repository;

import com.youcode.gameyou.Entity.Admin;
import com.youcode.gameyou.Entity.UserParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}