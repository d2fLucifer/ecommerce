package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto,Long userId);

    OrderDto updateOrder(OrderDto orderDto, Long orderId );

    void deleteOrder(Long orderId);

    OrderDto findOrderById(Long id);
    List<OrderDto> findOrdersByUserId(Long userid);

    List<OrderDto> findAllOrders();


}
