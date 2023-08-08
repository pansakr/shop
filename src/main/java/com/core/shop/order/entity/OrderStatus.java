package com.core.shop.order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    WAITING("주문대기"),
    FINISH("주문완료");

    private final String status;
}
