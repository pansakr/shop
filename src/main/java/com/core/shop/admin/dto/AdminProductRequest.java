package com.core.shop.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminProductRequest {

    private String name;

    private Integer price;

    private Integer stockNumber;

    private String details;

    private Integer sellStatus;

}
