package com.insup.catalog.catalog.dto;

import lombok.Data;

@Data
public class CatalogRequest {
    private String productId;
    private Integer quantity;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;
}
