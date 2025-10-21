package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityBeans {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 默认强度 10；可调到 10–14 之间
        return new BCryptPasswordEncoder();
    }
}