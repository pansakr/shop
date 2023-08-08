package com.core.shop.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminProductResponse {

    private String name;

    private Integer price;

    private Integer stockNumber;

    private String details;
}
