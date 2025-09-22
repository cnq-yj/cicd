package com.example.demo.product.repository;

import com.example.demo.product.entity.ProductLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductLikeRepository extends JpaRepository<ProductLike, Long> {

    List<ProductLike> findByUserIdAndProductIdInAndIsActiveTrue(Long userId, List<Long> productIds);

    boolean existsByUserIdAndProductIdAndIsActiveTrue(Long userId, Long productId);
}
