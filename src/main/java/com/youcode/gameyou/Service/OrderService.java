package com.youcode.gameyou.Service;

import com.youcode.gameyou.DTO.OrderDTO;
import com.youcode.gameyou.Entity.Order_;
import com.youcode.gameyou.Entity.Store;
import com.youcode.gameyou.Entity.UserParent;
import com.youcode.gameyou.Enum.OrderStatus;
import com.youcode.gameyou.Exception.ApiException;
import com.youcode.gameyou.Mapper.Mapper;
import com.youcode.gameyou.Repository.OrderRepository;
import com.youcode.gameyou.Repository.StoreRepository;
import com.youcode.gameyou.Repository.UserParentRepository;
import com.youcode.gameyou.Service.Interfaces.IOrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;
    private final UserParentRepository userParentRepository;
    private final Mapper<OrderDTO, Order_> mapper;
    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        try {
            // add status, total, createdAt, updatedAt
            orderDTO.setStatus(OrderStatus.PENDING.toString());
            Double total = 0.00;

            for (int i = 0; i < orderDTO.getOrderProducts().size(); i++) {
                Double price = orderDTO.getOrderProducts().get(i).getProduct().getPrice();
                int quantity = orderDTO.getOrderProducts().get(i).getQuantity();
                int discount = orderDTO.getOrderProducts().get(i).getDiscount();
                // total price
                total +=  quantity * (price - (price * discount / 100));
            }

            orderDTO.setTotal(total);
            orderDTO.setCreatedAt(new Date());
            orderDTO.setUpdatedAt(new Date());
            // map orderDTO to order
            Order_ order = mapper.convertAtoB(orderDTO, Order_.class);
            // find store by id
            Store store = storeRepository.findById(orderDTO.getStoreId())
                    .orElseThrow(() -> new ApiException("store not found", HttpStatus.BAD_REQUEST));
            order.setStore(store); // set store to order
            // find user by id
            UserParent client = userParentRepository.findById(orderDTO.getClientId()).orElseThrow(() -> new ApiException("user not found", HttpStatus.BAD_REQUEST));;
            order.setUserParent(client); // set client to order
            orderRepository.save(order); // save order
        } catch (Exception e) {
            throw new ApiException("error : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return orderDTO;
    }

    @Override
    public void delete(Long id) {
        Order_ order = orderRepository.findById(id)
                .orElseThrow(() -> new ApiException("order not found", HttpStatus.BAD_REQUEST));
        orderRepository.delete(order);
    }

    @Override
    public void updateOrderStatus(OrderStatus status, Long id) {
        Order_ findOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ApiException("order not found", HttpStatus.BAD_REQUEST));
        findOrder.setStatus(status.toString());
    }

    @Override
    public OrderDTO getOne(Long id) {
        Order_ order = orderRepository.findById(id)
                .orElseThrow(() -> new ApiException("order not found", HttpStatus.BAD_REQUEST));
        // map order to orderDTO
        OrderDTO orderDTO = mapper.convertBtoA(order, OrderDTO.class);
        return orderDTO;
    }

    @Override
    public List<OrderDTO> getAll(int page, int size) {
        if(page > 0) page--;
        List<Order_> orders = orderRepository.findAll(PageRequest.of(page, size)).stream().toList();
        // map list of orders to list of ordersDTO
        List<OrderDTO> orderDTOS = mapper.convertListBToListA(orders, OrderDTO.class);
        return orderDTOS;
    }
}