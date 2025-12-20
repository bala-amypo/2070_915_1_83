package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    public void createToken(String email, String role, Long userId) {
        // Declaration only
    }

    public void getClaims(String token) {
        // Declaration only
    }

    public void validateToken(String token) {
        // Declaration only
    }
}