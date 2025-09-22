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
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "image_type", length = 50, nullable = false)
    private String imageType;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
