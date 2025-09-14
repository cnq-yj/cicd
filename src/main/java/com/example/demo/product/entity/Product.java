package com.example.demo.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "brand", length = 100)
    private String brand;

    @Column(name = "price")
    private Long price;

    @Column(name = "original_price")
    private Long originalPrice;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "images", columnDefinition = "TEXT")
    private String images;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "features", columnDefinition = "TEXT")
    private String features;

    @Column(name = "sizes", columnDefinition = "TEXT")
    private String sizes;

    @Column(name = "colors", columnDefinition = "TEXT")
    private String colors;

    @Column(name = "stock")
    private Integer stock;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
