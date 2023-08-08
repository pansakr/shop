package com.core.shop.order.controller.api;

import com.core.shop.order.dto.OrderDetailsResponse;
import com.core.shop.order.dto.OrderResponse;
import com.core.shop.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    /**
     * 주문 등록 API
     * */
    @PostMapping("/orders")
    public ResponseEntity<List<OrderResponse>> orders(Authentication auth){
        List<OrderResponse> response = orderService.orders(auth);
        return ResponseEntity.ok().body(response);
    }

    /**
     * 주문 조회 API
     * */
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> orderList(Authentication auth){
        List<OrderResponse> response =  orderService.orderList(auth);
        return ResponseEntity.ok().body(response);
    }

    /**
     * 주문 상세 조회 API
     * */
    @GetMapping("/orders/{orderProductId}")
    public ResponseEntity<OrderDetailsResponse> orderDetails(@PathVariable Long orderProductId){
        OrderDetailsResponse response = orderService.orderDetails(orderProductId);
        return ResponseEntity.ok().body(response);
    }
}
