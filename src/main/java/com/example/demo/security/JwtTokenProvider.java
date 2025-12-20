package com.example.demo.security;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    private String secret;
    private long validityInMs;

    public JwtTokenProvider() {} // Default for Spring

    public JwtTokenProvider(String secret, long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }

    public String createToken(String email, String role, Long userId) {
        return ""; // Implementation logic
    }

    public Claims getClaims(String token) {
        return null; // Implementation logic
    }

    public boolean validateToken(String token) {
        return true; 
    }
}