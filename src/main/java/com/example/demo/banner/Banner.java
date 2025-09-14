package com.example.demo.banner;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Banner {

    @Id
    @Column(name = "banner_id")
    private Long bannerId;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "subtitle", length = 50)
    private String subtitle;

    @Column(name = "image", length = 50)
    private String image;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;
}
