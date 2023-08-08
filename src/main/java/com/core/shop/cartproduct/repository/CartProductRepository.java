package com.core.shop.cartproduct.repository;

import com.core.shop.cart.entity.CartEntity;
import com.core.shop.cartproduct.entity.CartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartProductRepository extends JpaRepository<CartProductEntity, CartProductEntity.Pk> {

    Optional<List<CartProductEntity>> findByCart(CartEntity cart);

    @Query("select c from CartProductEntity c where c.pk.cartId = :cartId and c.pk.productId = :productId")
    Optional<CartProductEntity> getCartProduct(@Param("cartId") Long cartId, @Param("productId") Long productId);
}
