package com.animon.configuration.securityconfig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().
                requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
        // Admin Kullanıcı
        UserDetails admin = User
                .withDefaultPasswordEncoder()
                .username("admin")
                .password("root")
                .roles("ADMIN")
                .build();

        // Writer Kullanıcı
        UserDetails supporter = User
                .withDefaultPasswordEncoder()
                .username("supporter")
                .password("root")
                .roles("SUPPORTER")
                .build();

        return new InMemoryUserDetailsManager(admin, supporter);
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/role/create").hasRole("ADMIN")
                        .requestMatchers("/user/create/**").hasRole("ADMIN")
                        .requestMatchers("/role/find/**").hasRole("ADMIN")
                        .requestMatchers("/user/find/**").hasRole("ADMIN")
                        .requestMatchers("/user/list").hasRole("ADMIN")
                        .requestMatchers("/role/list").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());

        // Disable CSRF
        http.csrf().disable();

        return http.build();
    }
}
