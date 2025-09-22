package com.example.demo.product.service;

import com.example.demo.product.dto.ProductDetailResponse;
import com.example.demo.product.dto.ProductListResponse;
import com.example.demo.product.dto.ProductOptionResponse;
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
    private final ProductImageService productImageService;
    private final ProductOptionService productOptionService;

    private final ProductRepository productRepository;

    public List<ProductListResponse> getProductList(Pageable pageable, User user) {
        List<Product> products = productRepository.findAll(pageable).getContent();

        List<Long> productIds = products.stream()
                .map(Product::getId)
                .collect(Collectors.toList());

        Map<Long, ProductReviewInfoResponse> productsReviewInfo = reviewService.getProductsReviewInfo(productIds);
        Map<Long, Boolean> productsLike = productLikeService.getProductsLike(user.getId(), productIds);
        Map<Long, String> productsThumbnail = productImageService.getProductsThumbnail(productIds);

        return products.stream()
                .map(product -> ProductListResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .brand(product.getBrand())
                        .price(product.getPrice())
                        .originalPrice(product.getOriginalPrice())
                        .thumbnail(productsThumbnail.get(product.getId()))
                        .category(product.getCategory())
                        .rating(productsReviewInfo.get(product.getId()).getAverageRating())
                        .reviewCount(productsReviewInfo.get(product.getId()).getReviewCount())
                        .isLiked(productsLike.get(product.getId()))
                        .build()).collect(Collectors.toList());
    }

    public ProductDetailResponse getProductDetail(Long id, User user) {
        Product product = productRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        ProductReviewInfoResponse productsReviewInfo = reviewService.getProductReviewInfo(product.getId());
        List<String> images = productImageService.getProductImage(product.getId());
        boolean isLiked = productLikeService.getProductLike(user.getId(), product.getId());
        List<ProductOptionResponse> options = productOptionService.getProductOptions(product.getId());

        return ProductDetailResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .brand(product.getBrand())
                .price(product.getPrice())
                .originalPrice(product.getOriginalPrice())
                .images(images)
                .category(product.getCategory())
                .rating(productsReviewInfo.getAverageRating())
                .reviewCount(productsReviewInfo.getReviewCount())
                .isLiked(isLiked)
                .description(product.getDescription())
                .options(options)
                .build();
    }
}
