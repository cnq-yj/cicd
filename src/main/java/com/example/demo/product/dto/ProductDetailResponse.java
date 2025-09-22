package com.example.demo.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ProductDetailResponse {
    private Long id;
    private String name;
    private String brand;
    private Integer price;
    private Integer originalPrice;
    private List<String> images;
    private String category;
    private Double rating;
    private Integer reviewCount;
    private boolean isLiked;
    private List<String> tags;
    private String description;
    private List<ProductOptionResponse> options;
}
