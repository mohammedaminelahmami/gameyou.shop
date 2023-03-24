package com.youcode.gameyou.Repository;

import com.youcode.gameyou.Entity.Client;
import com.youcode.gameyou.Entity.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findByEmail(String email);
    Page findAll(Pageable pageable);
}