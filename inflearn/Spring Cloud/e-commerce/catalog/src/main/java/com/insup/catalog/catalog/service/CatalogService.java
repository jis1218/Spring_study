package com.insup.catalog.catalog.service;

import com.insup.catalog.catalog.domain.Catalog;
import com.insup.catalog.catalog.domain.CatalogRepository;
import com.insup.catalog.catalog.dto.CatalogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CatalogService {

    private final CatalogRepository catalogRepository;

    public List<CatalogResponse> findAllCatalog() {
        List<Catalog> catalogs = catalogRepository.findAll();

        return catalogs.stream().map(CatalogResponse::of).collect(Collectors.toList());
    }

}
