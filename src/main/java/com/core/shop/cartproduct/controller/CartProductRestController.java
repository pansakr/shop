package com.core.shop.cartproduct.controller;

import com.core.shop.cartproduct.dto.CartProductRequest;
import com.core.shop.cartproduct.dto.CartProductResponse;
import com.core.shop.cartproduct.service.CartProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartProductRestController {

    private final CartProductService cartProductService;


    /**
     * 장바구니 상품 담기 API
     * */
    @PostMapping("/cartProducts")
    public void cartProductRegister(@RequestBody CartProductRequest request){
        cartProductService.cartProductRegister(request);
    }

    /**
     * 장바구니 상품 조회 API
     * */
    @GetMapping("/cartProducts")
    public ResponseEntity<List<CartProductResponse>> getCartProduct(Authentication auth){
        List<CartProductResponse> response = cartProductService.getCartProduct(auth);
        return ResponseEntity.ok().body(response);
    }

    /**
     * 장바구니 상품 삭제 API
     * */
    @DeleteMapping("/cartProducts/{productId}")
    public ResponseEntity<List<CartProductResponse>> deleteCartProduct(@PathVariable Long productId, Authentication auth){
        List<CartProductResponse> response = cartProductService.deleteCartProduct(productId, auth);
        return ResponseEntity.ok().body(response);
    }
}
