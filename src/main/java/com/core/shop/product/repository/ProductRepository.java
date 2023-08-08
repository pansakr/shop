package com.core.shop.product.repository;


import com.core.shop.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("select p.stockNumber from ProductEntity p where p.id = :productId")
    Optional<Integer> findStock(@Param("productId") Long productId); // 한개만 리턴받을 경우 리턴타입을 맞춰줘야 한다.


}
