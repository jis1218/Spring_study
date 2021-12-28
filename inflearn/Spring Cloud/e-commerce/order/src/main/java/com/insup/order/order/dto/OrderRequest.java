package com.insup.order.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderRequest {
    private final String productId;

    private final Integer quantity;

    private final int unitPrice;

    private final int totalPrice;

    private final String userId;

    private final String orderId;
}
