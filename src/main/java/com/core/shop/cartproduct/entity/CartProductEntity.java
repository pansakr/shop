package com.core.shop.cartproduct.entity;

import com.core.shop.cart.entity.CartEntity;
import com.core.shop.product.entity.ProductEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart_product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
public class CartProductEntity {

    @EmbeddedId
    private Pk pk;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    @Setter
    @Getter
    public static class Pk implements Serializable {

        @Column(name = "cart_no")
        private Long cartId;

        @Column(name = "product_no")
        private Long productId;
    }

    @MapsId("cartId")
    @ManyToOne
    @JoinColumn(name = "cart_no")
    private CartEntity cart;


    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_no")
    private ProductEntity product;

    @Column
    private Long count;

    public void addCount(Long count){
        this.count += count;
    }

    public CartProductEntity(CartEntity cart, ProductEntity product, Long count) {
        this.pk = new Pk(cart.getId(), product.getId()); // 생성자의 매개변수는 클래스의 필드가 나열된 순서대로 들어가므로 두개를 맞춰줘야 한다.
        this.cart = cart;
        this.product = product;
        this.count = count;


    }
}
