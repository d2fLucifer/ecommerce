package com.lucifer.ecommerce.Service.Impl;

import com.lucifer.ecommerce.Service.OrderService;
import com.lucifer.ecommerce.dto.OrderDto;
import com.lucifer.ecommerce.dto.ProductDto;
import com.lucifer.ecommerce.exception.ResourceNotFoundException;
import com.lucifer.ecommerce.model.*;
import com.lucifer.ecommerce.repository.OrderRepository;
import com.lucifer.ecommerce.repository.PaymentRepository;
import com.lucifer.ecommerce.repository.ProductRepository;
import com.lucifer.ecommerce.repository.UserRepository;
import com.lucifer.ecommerce.utils.GenericMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final GenericMapper genericMapper;

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto, Long userId) {


        Order order = genericMapper.map(orderDto, Order.class);

        // Retrieve the user, throwing an exception if not found
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Payment payment = paymentRepository.findById(orderDto.getPaymentId())
                .orElseThrow(() -> new ResourceNotFoundException("Payment", "Id", orderDto.getPaymentId()));

        order.setPayment(payment);
        order.setStatus(Status.PENDING);

        // Set order date to the current date
        order.setOrderDate(new java.sql.Date(System.currentTimeMillis()));

        // Retrieve products and handle empty list scenario
        List<Long> productIds = orderDto.getProducts().stream().map(ProductDto::getId).collect(Collectors.toList());
        if (productIds.isEmpty()) {
            throw new ResourceNotFoundException("Order", "Product IDs", "Empty product list in order");
        }

        List<Product> products = productRepository.findByIdIn(productIds).get();

        // Associate order with each product
        products.forEach(product -> product.getOrders().add(order));
        order.setProducts(new ArrayList<>());
        order.getProducts().addAll(products);
        Order savedOrder = orderRepository.save(order);
        productRepository.saveAll(products);

        payment.getOrders().add(savedOrder);
        paymentRepository.save(payment);

        user.getOrders().add(order);
        userRepository.save(user);

        return genericMapper.map(savedOrder, OrderDto.class);
    }


    @Override
    @Transactional
    public OrderDto updateOrder(OrderDto orderDto, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));

        order.setStatus(orderDto.getStatus());
        orderRepository.save(order);

        return genericMapper.map(order, OrderDto.class);
    }

    @Override
    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));


        order.getProducts().forEach(product -> {
            product.getOrders().removeIf(orderInProduct -> orderInProduct.getId().equals(order.getId()));
        });


        order.getPayment().getOrders().remove(order);


        productRepository.saveAll(order.getProducts());
        paymentRepository.save(order.getPayment());


        // Delete the order
        orderRepository.deleteById(orderId);
    }


    @Override
    public OrderDto findOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        return genericMapper.map(order, OrderDto.class);
    }

    @Override
    public List<OrderDto> findOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return genericMapper.mapList(orders, OrderDto.class);
    }

    @Override
    public List<OrderDto> findAllOrders() {
        return genericMapper.mapList(orderRepository.findAll(), OrderDto.class);
    }
}
