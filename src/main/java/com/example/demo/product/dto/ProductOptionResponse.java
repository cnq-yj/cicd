package com.example.demo.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ProductOptionResponse {
    private String size;
    private String color;
    private Integer stock;
}
