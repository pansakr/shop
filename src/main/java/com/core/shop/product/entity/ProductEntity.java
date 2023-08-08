package com.core.shop.product.entity;

import com.core.shop.common.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Setter
@Getter
public class ProductEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_no")
    private Long id;

    @Column
    private String name;

    @Column
    private Integer price;

    @Column(name = "stock_number")
    private Integer stockNumber;

    @Column
    private String details;

    @Column(name = "sell_status")
    @Enumerated(EnumType.STRING)
    private ProductSellStatus sellStatus;
}
