package com.core.shop.orderproduct.repository;

import com.core.shop.order.entity.OrderEntity;
import com.core.shop.orderproduct.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity,Long> {
    Optional<List<OrderProductEntity>> findByOrder(OrderEntity orderEntity);
}
