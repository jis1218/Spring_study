package com.insup.order.order.dto;

import com.insup.order.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class OrderResponse {
    private final String productId;

    private final Integer quantity;

    private final int unitPrice;

    private final int totalPrice;

    private final String orderId;

    private final Date createdAt;

    public static OrderResponse of(Order order) {
        return new OrderResponse(
                order.getProductId(),
                order.getQuantity(),
                order.getUnitPrice(),
                order.getTotalPrice(),
                order.getOrderId(),
                order.getCreatedAt()
        );
    }
}
