package com.example.demo.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ProductColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private Long id;

    @Column(name = "color_code", unique = true)
    private String code;

    @Column(name = "color_name")
    private String name;
}
