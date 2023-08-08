package com.core.shop.cart.repository;

import com.core.shop.cart.entity.CartEntity;
import com.core.shop.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    void deleteByMember(MemberEntity member);

    CartEntity findByMember(MemberEntity member);
}
