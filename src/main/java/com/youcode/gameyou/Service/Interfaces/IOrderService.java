package com.youcode.gameyou.Service.Interfaces;

import com.youcode.gameyou.DTO.OrderDTO;
import com.youcode.gameyou.Enum.OrderStatus;

import java.util.List;

public interface IOrderService {
    OrderDTO save(OrderDTO orderDTO);
    void delete(Long id);
//    void updateOrderStatus(OrderStatus status, Long id);
    OrderDTO getOne(Long id);
    List<OrderDTO> getAll(int page, int size);
    // Iterable<OrderDTO> getAll();
}
