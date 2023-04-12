package com.youcode.gameyou.Request.Order;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AddOrderRequest {
    @NotNull(message = "paymentType required !")
    private String paymentType;

    @NotNull(message = "orderProducts required !")
    private List<OrderProductRequest> orderProducts = new ArrayList<>();

    private Long storeId;
    private Long clientId;
}