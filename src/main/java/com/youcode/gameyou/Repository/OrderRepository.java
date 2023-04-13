package com.youcode.gameyou.Repository;

import com.youcode.gameyou.Entity.Order_;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order_, Long> {
    Page<Order_> findAll(Pageable pageable);
    @Query(nativeQuery = true, value = "select * from order_ where client_id = :client_id")
    List<Order_> queryfindAllByClientId(@Param("client_id") Long clientId);

    @Query(nativeQuery = true, value = "select * from order_ where store_id = :store_id")
    List<Order_> queryfindAllByStoreId(@Param("store_id") Long storeId);
}