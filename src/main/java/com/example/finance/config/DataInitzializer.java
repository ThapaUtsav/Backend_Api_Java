package com.example.finance.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.finance.model.entity.User;
import com.example.finance.repository.UserRepository;

@Configuration
public class DataInitzializer {
    @Bean
    @SuppressWarnings("null")
    public CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin123")) // Encrypts it!
                        .role("ROLE_ADMIN")
                        .build();
                userRepository.save(admin);
                System.out.println("Admin user created: admin / admin123");
            }
            if (userRepository.findByUsername("analyst").isEmpty()) {
                userRepository.save(User.builder()
                        .username("analyst")
                        .password(passwordEncoder.encode("analyst123"))
                        .role("ROLE_ANALYST")
                        .build());
                System.out.println("Analyst created: analyst/analyst123");
            }

            if (userRepository.findByUsername("viewer").isEmpty()) {
                userRepository.save(User.builder()
                        .username("viewer")
                        .password(passwordEncoder.encode("viewer123"))
                        .role("ROLE_VIEWER")
                        .build());
                System.out.println("Viewer created: viewer/viewer123");
            }
        };
    }
}