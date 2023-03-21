package com.youcode.gameyou.DTO;

import lombok.Getter;
import lombok.Setter;

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
}