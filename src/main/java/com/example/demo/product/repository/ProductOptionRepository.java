package com.example.demo.product.repository;

import com.example.demo.product.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

    @Query("SELECT po FROM ProductOption po " +
            "JOIN FETCH po.size " +
            "JOIN FETCH po.color " +
            "WHERE po.product.id = :productId")
    List<ProductOption> findByProductIdWithSizeAndColor(@Param("productId") Long productId);
}
