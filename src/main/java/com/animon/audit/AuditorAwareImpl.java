package com.animon.audit;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Log4j2
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {

        // database ile login girişi yapmış kullanıcı bilgilerini almak
        // org.springframework.security.core.Authentication;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Eğer kullanıcı giriş yapmışsa session bilgilerini almak
        if (authentication != null && authentication.isAuthenticated()) {
            log.warn("Sistemde Kullanı vardır.");
            System.out.println("Name: " + authentication.getName());
            System.out.println("Principal: " + authentication.getPrincipal());
            return Optional.of(authentication.getName());
        }

        // Eğer sistemde bir kullanıcı giriş  yapmamışsa default olarak hee hee dönder
        //return Optional.ofNullable(authentication!=null? authentication.getName() : null)
        return Optional.of("Hee hee.");
    }
}
