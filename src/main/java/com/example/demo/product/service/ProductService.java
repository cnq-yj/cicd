package com.example.demo.product.service;

import com.example.demo.product.dto.ProductListResponse;
import com.example.demo.product.entity.Product;
import com.example.demo.product.repository.ProductRepository;
import com.example.demo.review.dto.ProductReviewInfoResponse;
import com.example.demo.review.service.ReviewService;
import com.example.demo.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ReviewService reviewService;
    private final ProductLikeService productLikeService;

    private final ProductRepository productRepository;

    public List<ProductListResponse> list(Pageable pageable, User user) {
        List<Product> products = productRepository.findAll(pageable).getContent();

        List<Long> productIds = products.stream()
                .map(Product::getId)
                .collect(Collectors.toList());

        Map<Long, ProductReviewInfoResponse> productsReviewInfo = reviewService.getProductsReviewInfo(productIds);
        Map<Long, Boolean> productsLike = productLikeService.getProductLike(user.getId(), productIds);

        return products.stream()
                .map(product -> ProductListResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .brand(product.getBrand())
                        .price(product.getPrice())
                        .originalPrice(product.getOriginalPrice())
                        .thumbnail(product.getThumbnailUrl())
                        .category(product.getCategory())
                        .rating(productsReviewInfo.get(product.getId()).getAverageRating())
                        .reviewCount(productsReviewInfo.get(product.getId()).getReviewCount())
                        .isLiked(productsLike.getOrDefault(productsLike.get(product.getId()), false))
                        .tags(null)
                        .build()).collect(Collectors.toList());
    }
}
