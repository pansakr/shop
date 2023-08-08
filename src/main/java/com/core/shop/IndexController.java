package com.core.shop;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Authentication authentication, Model model){

        // 유저정보가 있으면 실행
//        if(authentication.getPrincipal() != null) {
//            Object principal = authentication.getPrincipal();
//            UserDetails userDetails = (UserDetails) principal;
//            String username = userDetails.getUsername();
//            model.addAttribute("member", username);
//        }

        return "index";
    }
}
