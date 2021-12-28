package com.insup.catalog.catalog.dto;

import com.insup.catalog.catalog.domain.Catalog;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CatalogResponse {
    private final String productId;
    private final String productName;
    private final Integer stock;
    private final Integer unitPrice;
    private final Date createdAt;

    public static CatalogResponse of(Catalog catalog) {
        return new CatalogResponse(
                catalog.getProductId(),
                catalog.getProductName(),
                catalog.getStock(),
                catalog.getUnitPrice(),
                catalog.getCreatedAt()
        );
    }
}
