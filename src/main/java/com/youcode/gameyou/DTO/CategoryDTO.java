package com.youcode.gameyou.DTO;

import com.youcode.gameyou.Entity.Product;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private String imagePath;
    private Boolean isActive;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private List<Product> products = new ArrayList<>();
}