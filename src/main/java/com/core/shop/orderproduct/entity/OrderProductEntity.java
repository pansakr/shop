package com.core.shop.orderproduct.entity;

import com.core.shop.order.entity.OrderEntity;
import com.core.shop.product.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_product")
@Getter
@Setter
public class OrderProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_no")
    private Long orderProductId;

    @ManyToOne
    @JoinColumn(name = "order_no")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_no2")
    private ProductEntity product;

    @Column(name = "order_price")
    private Integer orderPrice;

    @Column
    private Long count;
}
