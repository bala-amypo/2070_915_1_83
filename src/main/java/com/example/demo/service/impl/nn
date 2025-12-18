package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final JwtTokenProvider jwt;

    public UserServiceImpl(UserRepository repo, JwtTokenProvider jwt) {
        this.repo = repo;
        this.jwt = jwt;
    }

    @Override
    public void register(String email, String password, String role) {
        if (repo.existsByEmail(email))
            throw new IllegalArgumentException("unique");

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        repo.save(user);
    }

    @Override
    public String login(String email, String password) {
        User user = repo.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("not found"));

        if (!user.getPassword().equals(password))
            throw new IllegalStateException("not found");

        return jwt.createToken(email, user.getRole());
    }
}
