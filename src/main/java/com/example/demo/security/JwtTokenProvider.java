package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    public void createToken(String email, String role, Long userId) {}
    public void getClaims(String token) {}
    public void validateToken(String token) {}
}