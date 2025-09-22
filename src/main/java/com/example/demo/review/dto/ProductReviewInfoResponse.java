package com.example.demo.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ProductReviewInfoResponse {
    private Double averageRating;
    private Integer reviewCount;
}
