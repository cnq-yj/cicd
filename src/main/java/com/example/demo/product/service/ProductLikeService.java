package com.example.demo.product.service;

import com.example.demo.product.entity.ProductLike;
import com.example.demo.product.repository.ProductLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductLikeService {

    private final ProductLikeRepository productLikeRepository;

    public Map<Long, Boolean> getProductsLike(Long userId, List<Long> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            return new HashMap<>();
        }

        List<ProductLike> likedProducts = productLikeRepository
                .findByUserIdAndProductIdInAndIsActiveTrue(userId, productIds);

        Set<Long> likedProductsSet = likedProducts.stream()
                .map(productLike -> productLike.getProduct().getId())
                .collect(Collectors.toSet());

        return productIds.stream()
                .collect(Collectors.toMap(
                        productId -> productId,
                        likedProductsSet::contains
                ));
    }

    public boolean getProductLike(Long userId, Long productId) {
        return productLikeRepository.existsByUserIdAndProductIdAndIsActiveTrue(userId, productId);
    }
}
