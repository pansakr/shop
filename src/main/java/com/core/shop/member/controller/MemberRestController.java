package com.core.shop.member.controller;

import com.core.shop.member.dto.MemberRequest;
import com.core.shop.member.dto.MemberResponse;
import com.core.shop.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    /**
     * 회원가입 API
     * */
    @PostMapping("/signup")
    public void signup(@RequestBody MemberRequest request, HttpServletResponse response) throws IOException {

        memberService.signup(request);

        String redirectUrl = "/loginForm";
        response.sendRedirect(redirectUrl); // 성공시 로그인화면으로 이동하는 예/아니오 창 보여주기
    }

    /**
     * 회원정보 API
     * */
    @GetMapping("/info")
    public String info(Authentication authentication){
        Object principal = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();

        return username;
    }

    /**
     * 회원 수정 API
     * */
    @PutMapping("/members")
    public ResponseEntity<MemberResponse> modifyMember(@RequestBody MemberRequest request){
        MemberResponse response = memberService.memberModify(request);

        return ResponseEntity.ok().body(response);
    }

    /**
     * 회원 탈퇴 API
     * */
    @DeleteMapping("/members")
    public void deleteMember(@RequestBody MemberRequest request){
        memberService.memberDelete(request);
    }

}
