package com.example.demo.review.service;

import com.example.demo.review.dto.ProductReviewInfoResponse;
import com.example.demo.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Map<Long, ProductReviewInfoResponse> getProductsReviewInfo(List<Long> productIds) {
        return productIds.stream().collect(Collectors.toMap(
                productId -> productId,
                productId -> {
                    Double avgRating = reviewRepository.getAverageRating(productId);
                    Long reviewCount = reviewRepository.countByProductId(productId);

                    return ProductReviewInfoResponse.builder()
                            .averageRating(roundRating(avgRating))
                            .reviewCount(reviewCount != null ? reviewCount : 0L)
                            .build();
                }
        ));
    }

    public ProductReviewInfoResponse getProductReviewInfo(Long productId) {
        return ProductReviewInfoResponse.builder()
                .averageRating(roundRating(reviewRepository.getAverageRating(productId)))
                .reviewCount(reviewRepository.countByProductId(productId))
                .build();
    }

    private Double roundRating(Double rating) {
        if (rating == null) return 0.0;
        return Math.round(rating * 10.0) / 10.0;
    }
}
