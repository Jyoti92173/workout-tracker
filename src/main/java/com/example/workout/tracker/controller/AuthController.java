package com.example.workout.tracker.controller;

import com.example.workout.tracker.dto.auth.AuthResponse;

import com.example.workout.tracker.dto.auth.LoginRequest;
import com.example.workout.tracker.service.AuthService;
import com.example.workout.tracker.service.impl.UserServiceImpl;
import com.example.workout.tracker.dto.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;

    // For Register....
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        AuthResponse authResponse =  authService.register(request);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    // For Login......
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse authResponse = authService.login(request);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

}
