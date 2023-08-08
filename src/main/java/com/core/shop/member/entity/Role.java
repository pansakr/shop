package com.core.shop.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {

    ADMIN("관리자"),
    MEMBER("회원");

    private final String code;

}
