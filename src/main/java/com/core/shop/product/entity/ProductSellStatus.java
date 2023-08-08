package com.core.shop.product.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductSellStatus {

    FOR_SALE("판매중"),
    SOLD_OUT("매진");

    private final String status;
}
