package com.example.demo.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ProductListResponse {
    private Long id;
    private String name;
    private String brand;
    private Long price;
    private Long originalPrice;
    private String thumbnail;
    private String category;
    private Double rating;
    private Long reviewCount;
    private boolean isLiked;
    private List<String> tags;
}
