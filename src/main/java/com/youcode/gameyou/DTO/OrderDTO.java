package com.youcode.gameyou.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private Long id;
    private Double total;
    private String status;
    private String paymentType;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private List<OrderProductDTO> orderProducts = new ArrayList<>();
}