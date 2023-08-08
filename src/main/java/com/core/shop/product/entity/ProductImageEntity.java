package com.core.shop.product.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "product_images")
@Entity
@Getter
@Setter
@ToString
public class ProductImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image_no")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no")
    private ProductEntity product;

    @Column
    private String name;

    @Column(name = "origin_name")
    private String originName;

    @Column
    private String url;

    @Column(name = "display_image_yn")
    private boolean isDisplayImage;
}
