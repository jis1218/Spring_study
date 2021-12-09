package com.insup.order.order.controller;

import com.insup.order.order.domain.Order;
import com.insup.order.order.dto.OrderRequest;
import com.insup.order.order.dto.OrderResponse;
import com.insup.order.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.createOrder(orderRequest);

        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity getOrderByOrderId(@PathVariable String orderId) {
        OrderResponse orderResponse = orderService.getOrderByOrderId(orderId);

        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity getOrdersByUserId(@PathVariable String userId) {
        List<OrderResponse> orderResponses = orderService.getOrdersByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(orderResponses);
    }
}
