package com.core.shop.cartproduct.service;

import com.core.shop.cartproduct.dto.CartProductRequest;
import com.core.shop.cartproduct.dto.CartProductResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CartProductService {
    void cartProductRegister(CartProductRequest request);

    List<CartProductResponse> getCartProduct(Authentication auth);

    List<CartProductResponse> deleteCartProduct(Long id, Authentication auth);
}
