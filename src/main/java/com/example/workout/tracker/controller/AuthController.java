package com.example.workout.tracker.controller;

import com.example.workout.tracker.dto.auth.AuthResponseDTO;
import com.example.workout.tracker.dto.auth.LoginRequestDTO;
import com.example.workout.tracker.dto.auth.RegisterRequestDTO;
import com.example.workout.tracker.service.AuthService;
import jakarta.validation.Valid;
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

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
        AuthResponseDTO authResponse = authService.register(request);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO LoginRequest) {
        AuthResponseDTO authResponse = authService.login(LoginRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}