package com.youcode.gameyou.Response.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String name;
    private Integer quantity;
    private String title;
    private String description;
    private String price;
}