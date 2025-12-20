package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.dto.AuthResponse;

public interface UserService {
    User register(String email, String password, String role); // [cite: 329]
    AuthResponse login(String email, String password); // [cite: 330]
    User getByEmail(String email); // [cite: 331]
}