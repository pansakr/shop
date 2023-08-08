package com.core.shop.admin.controller.api;

import com.core.shop.admin.dto.AdminProductRequest;
import com.core.shop.admin.dto.AdminProductResponse;
import com.core.shop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminProductRestController {

    private final ProductService productService;

    /**
     * 상품 등록 API
     */
    @PostMapping("/products")
    public ResponseEntity<AdminProductResponse> productRegister(@RequestBody AdminProductRequest request){

        AdminProductResponse response = productService.productRegister(request);

        return ResponseEntity.ok().body(response);
    }

    /**
     * 상품 수정 API
     */
    @PutMapping("/products/{productId}")
    public ResponseEntity<AdminProductResponse> productModify(@PathVariable Long productId, @RequestBody AdminProductRequest request){

        AdminProductResponse response = productService.productModify(productId, request);

        return ResponseEntity.ok().body(response);
    }

    /**
     * 상품 삭제 API
     */
    @DeleteMapping("/products/{productId}")
    public ResponseEntity productDelete(@PathVariable Long productId){

        productService.productDelete(productId);

        return ResponseEntity.ok().build();
    }

    /**
     * 특정 상품 재고 조회 API
     */
    @GetMapping("/products/{productId}/stocks")
    public ResponseEntity<Integer> productStock(@PathVariable Long productId){

        AdminProductResponse response = productService.productStock(productId);

        return ResponseEntity.ok().body(response.getStockNumber());
    }

    /**
     * 특정 상품 재고수정 API
     */
    @PutMapping("/products/{productId}/stocks")
    public ResponseEntity<Integer> productStockModify(@PathVariable Long productId, @RequestBody AdminProductRequest request) {

        AdminProductResponse response = productService.productStockModify(productId, request);

        return ResponseEntity.ok().body(response.getStockNumber());

    }
}
