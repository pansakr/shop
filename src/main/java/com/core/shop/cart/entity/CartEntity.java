package com.core.shop.cart.entity;

import com.core.shop.member.entity.MemberEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "carts")
@Setter
@Getter
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_Id")
    private Long Id;

    @OneToOne
    @JoinColumn(name = "member_no")
    private MemberEntity member;  //외래키의 타입이 entity라서 해당하는 엔티티를 그대로 넣어줘야함
                                  //entity.getId() 이런 식으로 넣어주면 get으로 가져오는 값은 단일 타입이라 오류발생
}
