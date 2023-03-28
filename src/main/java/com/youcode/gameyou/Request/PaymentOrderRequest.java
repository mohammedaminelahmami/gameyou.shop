package com.youcode.gameyou.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentOrderRequest {
    private Double total;
    private String currency;
    private String method;
    private String intent;
    private String description;
    private String cancelUrl;
    private String successUrl;
}