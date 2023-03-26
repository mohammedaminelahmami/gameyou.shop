package com.youcode.gameyou.DTO;

import com.youcode.gameyou.Entity.Category;
import com.youcode.gameyou.Entity.Image;
import com.youcode.gameyou.Entity.OrderProduct;
import com.youcode.gameyou.Entity.Store;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String quantity;
    private String title;
    private String description;
    private String price;
    private Boolean isActive;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private Category category;
    private Store store;
    private List<Image> images = new ArrayList<>();
    private List<OrderProduct> orderProducts = new ArrayList<>();
}