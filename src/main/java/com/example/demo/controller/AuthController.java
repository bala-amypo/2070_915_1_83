package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth") // [cite: 348]
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register") // [cite: 352]
    public void register(@RequestBody AuthRequest request) {
        userService.register(request.getEmail(), request.getPassword(), "USER"); // [cite: 356]
    }

    @PostMapping("/login") // [cite: 357]
    public AuthResponse login(@RequestBody AuthRequest request) {
        return userService.login(request.getEmail(), request.getPassword()); // [cite: 360]
    }
}