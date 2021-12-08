package com.insup.catalog.catalog.dto;

import com.insup.catalog.catalog.domain.Catalog;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CatalogResponse {
    private String productId;
    private String productName;
    private Integer stock;
    private Integer unitPrice;
    private Date createdAt;

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
