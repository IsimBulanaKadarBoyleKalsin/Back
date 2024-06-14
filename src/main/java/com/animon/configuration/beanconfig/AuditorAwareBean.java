package com.animon.configuration.beanconfig;

import com.animon.audit.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditorAwareBean {

    @Bean
    public AuditorAware<String> auditorAwareBeanMethod(){
        return new AuditorAwareImpl();
    }
}
