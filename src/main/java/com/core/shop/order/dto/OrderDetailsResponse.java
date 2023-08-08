package com.core.shop.order.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsResponse {

    private String productName;
    private Integer price;
    private Long count;
    private LocalDateTime orderDate;
}
