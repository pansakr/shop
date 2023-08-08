package com.core.shop.order.repository;

import com.core.shop.member.entity.MemberEntity;
import com.core.shop.order.entity.OrderEntity;
import com.core.shop.order.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    @Query("select o from OrderEntity o where o.member = :member")
    Optional<List<OrderEntity>> findByMember(@Param("member") MemberEntity member);

}
