package com.insup.catalog.catalog.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120, unique = true)
    private String productId;

    private String productName;

    private int stock;

    private int unitPrice;

    @CreatedDate
    private Date createdAt;
}
