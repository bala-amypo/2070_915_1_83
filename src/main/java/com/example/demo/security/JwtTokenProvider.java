package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    public String createToken(String email, String role, Long userId) { return null; [cite_start]} // [cite: 409]
    [cite_start]public void getClaims(String token) {} // [cite: 412]
    public boolean validateToken(String token) { return false; [cite_start]} // [cite: 414]
}