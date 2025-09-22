package com.example.demo.product.repository;

import com.example.demo.product.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findByProductIdInAndImageType(List<Long> productIds, String imageType);

    List<ProductImage> findByProductIdAndImageType(Long productId, String name);
}
