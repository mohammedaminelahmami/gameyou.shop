package com.youcode.gameyou.Controller;

import com.youcode.gameyou.DTO.OrderDTO;
import com.youcode.gameyou.DTO.OrderProductDTO;
import com.youcode.gameyou.Mapper.Mapper;
import com.youcode.gameyou.Request.Order.AddOrderRequest;
import com.youcode.gameyou.Request.Order.OrderProductRequest;
import com.youcode.gameyou.Response.Order.OrderResponse;
import com.youcode.gameyou.Service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final Mapper<OrderDTO, OrderResponse> mapper;
    private final Mapper<OrderProductRequest, OrderProductDTO> mapperReqToDTO;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderResponse save(@RequestBody @Valid AddOrderRequest addOrderRequest) {
        // map addOrderRequest to orderDTO
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(addOrderRequest, orderDTO);

        orderDTO.setOrderProducts(mapperReqToDTO.convertListAToListB(addOrderRequest.getOrderProducts(), OrderProductDTO.class));
        OrderDTO returnOrderDTO = orderService.save(orderDTO);
        // map returnOrderDTO to orderResponse
        OrderResponse orderResponse = mapper.convertAtoB(returnOrderDTO, OrderResponse.class);
        return orderResponse;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/status/{id}")
    public void updateStatus(@RequestParam String orderStatus, @PathVariable Long id) {
        orderService.updateOrderStatus(orderStatus, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public OrderResponse getOne(@PathVariable Long id) {
        OrderDTO orderDTO = orderService.getOne(id);
        // map orderDTO to orderResponse
        OrderResponse orderResponse = mapper.convertAtoB(orderDTO, OrderResponse.class);
        return orderResponse;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<OrderResponse> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<OrderDTO> orderDTOList = orderService.getAll(page, size);
        // map orderDTOList to orderResponseList
        List<OrderResponse> orderResponseList = mapper.convertListAToListB(orderDTOList, OrderResponse.class);
        return orderResponseList;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/client/{id}")
    public List<OrderResponse> getAllOrderClient(@PathVariable Long id) {
        List<OrderDTO> orderDTOList = orderService.getAllOrderByClientId(id);
        // map orderDTOList to orderResponseList
        List<OrderResponse> orderResponseList = mapper.convertListAToListB(orderDTOList, OrderResponse.class);
        return orderResponseList;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/seller/{id}")
    public List<OrderResponse> getAllOrderSeller(@PathVariable Long id) {
        List<OrderDTO> orderDTOList = orderService.getAllOrderByStoreId(id);
        // map orderDTOList to orderResponseList
        List<OrderResponse> orderResponseList = mapper.convertListAToListB(orderDTOList, OrderResponse.class);
        return orderResponseList;
    }
}