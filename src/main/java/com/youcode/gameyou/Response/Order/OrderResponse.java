package com.youcode.gameyou.Response.Order;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private Integer total;
    private String status;
    private String paymentType;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private List<OrderProductResponse> orderProducts = new ArrayList<>();
}