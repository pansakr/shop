package com.core.shop.product.controller.api;

import com.core.shop.product.dto.ProductResponse;
import com.core.shop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductResponse> importOneProduct(@PathVariable Long productId){

        ProductResponse response = productService.importOneProduct(productId);

        return ResponseEntity.ok().body(response);
    }

}
