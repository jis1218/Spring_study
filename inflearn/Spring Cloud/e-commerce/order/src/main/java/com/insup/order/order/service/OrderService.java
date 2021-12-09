package com.insup.order.order.service;

import com.insup.order.order.domain.Order;
import com.insup.order.order.domain.OrderRepository;
import com.insup.order.order.dto.OrderRequest;
import com.insup.order.order.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = new Order(
                orderRequest.getProductId(),
                orderRequest.getQuantity(),
                orderRequest.getUnitPrice(),
                orderRequest.getTotalPrice(),
                orderRequest.getUserId(),
                orderRequest.getOrderId()
        );

        Order savedOrder = orderRepository.save(order);

        return OrderResponse.of(savedOrder);
    }

    public OrderResponse getOrderByOrderId(String orderId) {
        Order order = orderRepository.findByOrderId(orderId).orElseThrow(() -> new NoSuchElementException("찾는 주문이 없습니다"));

        return OrderResponse.of(order);
    }

    public List<OrderResponse> getOrdersByUserId(String userId) {
        List<Order> order = orderRepository.findByUserId(userId);

        return order.stream().map(OrderResponse::of).collect(Collectors.toList());
    }

}
