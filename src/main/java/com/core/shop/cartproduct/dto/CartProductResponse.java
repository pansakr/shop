package com.core.shop.cartproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartProductResponse {

    private String productName;
    private Long count;
    private Integer price;
}
