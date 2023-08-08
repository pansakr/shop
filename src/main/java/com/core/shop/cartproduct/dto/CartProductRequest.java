package com.core.shop.cartproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartProductRequest {

    private String email;

    private Long productId;

    private Long count;
}
