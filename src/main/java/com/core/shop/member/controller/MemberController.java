package com.core.shop.member.controller;

import com.core.shop.member.dto.MemberResponse;
import com.core.shop.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signupForm")
    public String signupForm(){
        return "/member/signupForm";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "member/loginForm";
    }

    @GetMapping("/members/{memberId}")
    public String memberModify(Model model, @PathVariable Long memberId){

        MemberResponse response = memberService.memberModifyForm(memberId);
        model.addAttribute("member",response);

        return "member/modifyForm";
    }
}
