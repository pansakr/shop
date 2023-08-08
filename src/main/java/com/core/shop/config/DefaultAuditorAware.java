package com.core.shop.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class DefaultAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // context(문맥): XxxContext -> Xxx 를 포함한 그 주위의 무언가
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = "";
        if (authentication != null) {
            username = authentication.getName();
        }

        // authentication.getName() or ""
        return Optional.of(username);
    }

}
