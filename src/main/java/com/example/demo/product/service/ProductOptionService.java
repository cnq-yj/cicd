package com.example.demo.product.service;

import com.example.demo.product.dto.ProductOptionResponse;
import com.example.demo.product.entity.ProductOption;
import com.example.demo.product.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductOptionService {
    private final ProductOptionRepository productOptionRepository;

    public List<ProductOptionResponse> getProductOptions(Long productId) {
        List<ProductOption> options = productOptionRepository.findByProductIdWithSizeAndColor(productId);

        return options.stream()
                .map(option -> ProductOptionResponse.builder()
                        .size(option.getSize().getName())
                        .color(option.getColor().getName())
                        .stock(option.getStock())
                        .build())
                .collect(Collectors.toList());
    }
}
