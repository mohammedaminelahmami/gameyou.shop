package com.youcode.gameyou.Response.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductResponse {
    private String name;
    private String quantity;
    private String title;
    private String description;
    private double price;
}