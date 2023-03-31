package com.youcode.gameyou.Repository;

import com.youcode.gameyou.Entity.Image;
import com.youcode.gameyou.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByProduct(Product product);
}