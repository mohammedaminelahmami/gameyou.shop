package com.youcode.gameyou.DTO;

import com.youcode.gameyou.Entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductDTO {
    private Long id;
    private Integer quantity;
    private Integer discount;
    private Product product;
}
