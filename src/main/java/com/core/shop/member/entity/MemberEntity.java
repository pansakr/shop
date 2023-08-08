package com.core.shop.member.entity;

import com.core.shop.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "members")
@Getter
@Setter
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @NotNull
    @Size(max = 50)
    @Column
    private String name;

    @Column
    private String address1;

    @Column
    private String address2;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

}
