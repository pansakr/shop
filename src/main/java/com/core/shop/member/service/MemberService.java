package com.core.shop.member.service;

import com.core.shop.member.dto.MemberRequest;
import com.core.shop.member.dto.MemberResponse;
import com.core.shop.member.entity.MemberEntity;
import com.core.shop.member.entity.Role;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface MemberService extends UserDetailsService {

    void signup(MemberRequest request);

    default void memberRole(MemberEntity member, PasswordEncoder passwordEncoder){
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setRole(Role.MEMBER);
    }

    MemberResponse memberModifyForm(Long memberId);

    MemberResponse memberModify(MemberRequest request);

    void memberDelete(MemberRequest request);
}
