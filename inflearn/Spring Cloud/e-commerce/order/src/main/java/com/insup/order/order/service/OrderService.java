package com.insup.order.order.service;

import com.insup.order.order.domain.Order;
import com.insup.order.order.domain.OrderRepository;
import com.insup.order.order.dto.OrderRequest;
import com.insup.order.order.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = new Order(

        )
    }

    public OrderResponse getOrderByOrderId(String orderId) {

    }

    public List<OrderResponse> getOrdersByUserId(String userId) {

    }

}
