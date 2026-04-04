package com.example.finance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
// found about beans
// https://docs.spring.io/spring-security/reference/servlet/architecture.html
// still used AI for this
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disabled for testing in Postman
                .authorizeHttpRequests(auth -> auth
                        // Section 1: Access Control Rules
                        .requestMatchers(HttpMethod.DELETE, "/api/finance/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/finance/**").hasAnyRole("ADMIN", "ANALYST")
                        .requestMatchers(HttpMethod.PUT, "/api/finance/**").hasAnyRole("ADMIN", "ANALYST")
                        .requestMatchers(HttpMethod.GET, "/api/finance/**").hasAnyRole("ADMIN", "ANALYST", "VIEWER")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults()); // Allows basic auth in Postman

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
