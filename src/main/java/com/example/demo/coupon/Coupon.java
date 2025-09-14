package com.example.demo.coupon;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "discount")
    private String discount;

    @Column(name = "valid_until")
    private String validUntil;

    @Column(name = "min_amount")
    private String minAmount;

    @Column(name = "discount_type")
    private String discountType;

    @Column(name = "discount_value")
    private String discountValue;

    @Column(name = "created_at")
    private String createdAt;
}
