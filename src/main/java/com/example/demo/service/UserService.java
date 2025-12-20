package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.dto.AuthResponse;

public interface UserService {
    User register(String email, String password, String role);
    AuthResponse login(String email, String password);
    User getByEmail(String email);
}