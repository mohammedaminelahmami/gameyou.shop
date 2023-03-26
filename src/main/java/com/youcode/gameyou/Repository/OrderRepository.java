package com.youcode.gameyou.Repository;

import com.youcode.gameyou.Entity.Order_;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order_, Long> {
    Page<Order_> findAll(Pageable pageable);
}