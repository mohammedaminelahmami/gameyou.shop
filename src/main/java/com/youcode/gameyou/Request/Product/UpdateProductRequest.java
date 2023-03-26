package com.youcode.gameyou.Request.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UpdateProductRequest {
    private String name;
    private Integer quantity;
    private String title;
    private String description;
    private String price;
}
