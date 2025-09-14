package com.example.demo.product.service;

import com.example.demo.product.repository.ProductLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductLikeService {

    private final ProductLikeRepository productLikeRepository;

    public Map<Long, Boolean> getProductLike(Long userId, List<Long> productIds) {
        List<Long> likedProductIds = productLikeRepository
                .findActiveLikedProductIds(userId, productIds);
        Set<Long> likedSet = new HashSet<>(likedProductIds);

        return productIds.stream()
                .collect(Collectors.toMap(
                        productId -> productId,
                        likedSet::contains
                ));
    }
}
