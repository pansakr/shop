package com.core.shop.order.service;

import com.core.shop.order.dto.OrderDetailsResponse;
import com.core.shop.order.dto.OrderResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OrderService {
    List<OrderResponse> orders(Authentication auth);

    List<OrderResponse> orderList(Authentication auth);

    OrderDetailsResponse orderDetails(Long orderProductId);
}
