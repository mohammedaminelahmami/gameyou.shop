package com.youcode.gameyou.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String quantity;
    private String title;
    private String description;
    private double price;
    private Boolean isactive;
}