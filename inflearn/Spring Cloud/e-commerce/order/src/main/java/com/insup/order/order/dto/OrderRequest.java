package com.insup.order.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderRequest {
    private String productId;

    private Integer quantity;

    private int unitPrice;

    private int totalPrice;

    private String userId;

    private String orderId;
}
