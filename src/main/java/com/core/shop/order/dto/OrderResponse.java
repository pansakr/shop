package com.core.shop.order.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private String productName;
    private Long orderProductId;
    private Integer price;
    private Long count;
    private String email;
    private String address1;
    private String address2;

}
