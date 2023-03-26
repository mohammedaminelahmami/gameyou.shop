package com.youcode.gameyou.Request.Order;

import com.youcode.gameyou.Request.Product.AddProductRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductRequest {
    private Integer quantity;
    private Integer discount;
    private AddProductRequest product;
}