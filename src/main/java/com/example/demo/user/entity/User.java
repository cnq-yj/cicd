package com.example.demo.user.entity;

import com.example.demo.user.dto.RegisterRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "zipcode", length = 10)
    private String zipcode;

    @Column(name = "base_address")
    private String baseAddress;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "membership", length = 20, nullable = false)
    private String membership;

    @Column(name = "total_orders")
    private Integer totalOrders;

    @Column(name = "total_spent")
    private Long totalSpent;

    @Column(name = "review_count")
    private Integer reviewCount;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum Membership {
        BRONZE, SILVER, GOLD
    }

    public static User createUser(RegisterRequest request, String password) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(password)
                .zipcode(request.getZipcode())
                .baseAddress(request.getBaseAddress())
                .detailAddress(request.getDetailAddress())
                .membership(Membership.BRONZE.name())
                .totalOrders(0)
                .totalSpent(0L)
                .reviewCount(0)
                .role(Role.USER)
                .build();
    }

    public static User createKakaoUser(String name, String email, String password) {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .zipcode(null)
                .baseAddress(null)
                .detailAddress(null)
                .membership(Membership.BRONZE.name())
                .totalOrders(0)
                .totalSpent(0L)
                .reviewCount(0)
                .role(Role.USER)
                .build();
    }
}