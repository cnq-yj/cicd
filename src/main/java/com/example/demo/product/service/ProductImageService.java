package com.example.demo.product.service;

import com.example.demo.product.entity.ProductImage;
import com.example.demo.product.repository.ProductImageRepository;
import com.example.demo.product.type.ImageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductImageService {
    private final ProductImageRepository productImageRepository;

    public Map<Long, String> getProductsThumbnail(List<Long> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            return new HashMap<>();
        }

        return productImageRepository
                .findByProductIdInAndImageType(productIds, ImageType.THUMBNAIL.name())
                .stream()
                .collect(Collectors.toMap(
                        thumbnail -> thumbnail.getProduct().getId(),
                        ProductImage::getUrl
                ));
    }

    public List<String> getProductImage(Long productId) {
        return productImageRepository.findByProductIdAndImageType(productId, ImageType.MAIN.name())
                .stream()
                .map(ProductImage::getUrl) // 또는 적절한 getter 메서드
                .collect(Collectors.toList());
    }
}
