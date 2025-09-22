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
public class ProductOption  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    private ProductSize size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private ProductColor color;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
