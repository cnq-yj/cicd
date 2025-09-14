package com.example.demo.product.controller;

import com.example.demo.product.dto.ProductListResponse;
import com.example.demo.product.service.ProductService;
import com.example.demo.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<?> list(@PageableDefault(size = 20) Pageable pageable,
                                  @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<ProductListResponse> response = productService.list(pageable, principalDetails.getUser());
        return ResponseEntity.ok().body(response);
    }
}
