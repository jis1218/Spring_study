package com.insup.order.order.dto;

import com.insup.order.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class OrderResponse {
    private String productId;

    private Integer quantity;

    private int unitPrice;

    private int totalPrice;

    private String orderId;

    private Date createdAt;

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
