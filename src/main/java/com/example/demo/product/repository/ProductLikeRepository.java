package com.example.demo.product.repository;

import com.example.demo.product.entity.ProductLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductLikeRepository extends JpaRepository<ProductLike, Long> {

    @Query("SELECT pl.product.id FROM ProductLike pl WHERE pl.user.id = :userId AND pl.product.id IN :productIds AND pl.isActive = true")
    List<Long> findActiveLikedProductIds(@Param("userId") Long userId, @Param("productIds") List<Long> productIds);
}
