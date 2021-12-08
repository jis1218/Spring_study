package com.insup.catalog.catalog.controller;

import com.insup.catalog.catalog.dto.CatalogResponse;
import com.insup.catalog.catalog.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping("/health-check")
    public String status() {
        return "It's Working in Catalog Service";
    }

    @GetMapping("/")
    public ResponseEntity findAllCatalogs() {
        List<CatalogResponse> catalogResponses = catalogService.findAllCatalog();

        return ResponseEntity.status(HttpStatus.OK).body(catalogResponses);
    }
}
