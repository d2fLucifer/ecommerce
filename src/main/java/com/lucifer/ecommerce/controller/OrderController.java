package com.lucifer.ecommerce.controller;

import com.lucifer.ecommerce.Service.OrderService;
import com.lucifer.ecommerce.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/users/{userId}")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto,
                                                @PathVariable(name = "userId") Long userId) {
        return new ResponseEntity<>(orderService.createOrder(orderDto, userId), HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto,
                                                @PathVariable(name = "orderId") Long orderId) {
        return new ResponseEntity<>(orderService.updateOrder(orderDto, orderId), HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "orderId") Long orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable(name = "orderId") Long orderId) {
        return new ResponseEntity<>(orderService.findOrderById(orderId), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<OrderDto>> getAllOrdersByUserId(@PathVariable(name = "userId") Long userId) {
        return new ResponseEntity<>(orderService.findOrdersByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return new ResponseEntity<>(orderService.findAllOrders(), HttpStatus.OK);
    }
}
