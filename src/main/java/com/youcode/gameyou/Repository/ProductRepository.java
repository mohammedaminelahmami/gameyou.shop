package com.youcode.gameyou.Repository;

import com.youcode.gameyou.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
    Page<Product> findAllByStoreId(Pageable pageable, Long idStore);
    Page<Product> findAllByCategoryId(Pageable pageable, Long idCategory);

    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE price < 300 and category_id = :ctId")
    List<Product> queryfindAllByPriceLessThan300(@Param("ctId") Long ctId);

    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE price BETWEEN 300 AND 1500 and category_id = :ctId")
    List<Product> queryfindAllByPricebetween300To1500(@Param("ctId") Long ctId);

    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE price BETWEEN 1500 AND 20000 and category_id = :ctId    ")
    List<Product> queryfindAllByPricebetween1500To20000(@Param("ctId") Long ctId);
}