package com.youcode.gameyou.Response.Order;

import com.youcode.gameyou.Response.Product.ProductResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductResponse {
    private Integer quantity;
    private Integer discount;
    private ProductResponse product;
}