package com.youcode.gameyou.Repository;

import com.youcode.gameyou.Entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
}