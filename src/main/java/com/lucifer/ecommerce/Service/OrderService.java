package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.OrderDto;
import com.lucifer.ecommerce.dto.Response.OrderResponse;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto updateOrder(OrderDto orderDto, String orderId);
    void deleteOrder(String orderId);
    OrderResponse getOrderById(String id);




}
