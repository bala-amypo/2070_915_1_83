package com.example.demo.service;

public interface UserService {
    void register(String email, String password, String role);
    String login(String email, String password);
}
