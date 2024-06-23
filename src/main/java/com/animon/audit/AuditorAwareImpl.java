package com.animon.audit;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Log4j2
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Eğer kullanıcı giriş yapmışsa session bilgilerini almak
        if (authentication == null || !authentication.isAuthenticated()) {

            return Optional.empty();
        }

        return Optional.of(authentication.getName());
    }
}
