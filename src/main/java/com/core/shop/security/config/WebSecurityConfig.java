package com.core.shop.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();

        http.authorizeRequests()
                .anyRequest()
                .permitAll();

        http.formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login") // html form 태그의 action url
                .usernameParameter("email") // 프론트에서 로그인 시 넘어오는 파라미터 이름
                .defaultSuccessUrl("/info") //성공 시 재요청 url
                .permitAll();

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
