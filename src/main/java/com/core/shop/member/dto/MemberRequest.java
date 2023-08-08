package com.core.shop.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {

    private String name;

    private String email;

    private String password;

    private String address1;

    private String address2;
}
