package com.example.demo.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ProductSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_id")
    private Long id;

    @Column(name = "size_code", unique = true)
    private String code;

    @Column(name = "size_name")
    private String name;

    @Column(name = "sort_order")
    private Integer sortOrder;
}
