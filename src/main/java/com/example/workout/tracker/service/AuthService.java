package com.example.workout.tracker.service;

import com.example.workout.tracker.dto.auth.AuthResponse;
import com.example.workout.tracker.dto.auth.LoginRequest;
import com.example.workout.tracker.dto.auth.RegisterRequest;
import com.example.workout.tracker.repository.UserRepository;
import com.example.workout.tracker.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
}
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
    }

    public AuthResponse login(LoginRequest request) {

    }
}
