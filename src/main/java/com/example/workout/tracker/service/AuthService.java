package com.example.workout.tracker.service;

import com.example.workout.tracker.dto.auth.AuthResponseDTO;
import com.example.workout.tracker.dto.auth.LoginRequestDTO;
import com.example.workout.tracker.dto.auth.RegisterRequestDTO;
import com.example.workout.tracker.model.Roles;
import com.example.workout.tracker.model.User;
import com.example.workout.tracker.repository.UserRepository;
import com.example.workout.tracker.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO register(RegisterRequestDTO request) {

            // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

            // Create new user
            User user = new User();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(Roles.ROLE_USER);  // Hardcoded role for now

            // Save user
            userRepository.save(user);

            UserDetails userDetails = org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .authorities(user.getRole().name())
                    .build();

            String token = jwtUtil.generateToken(userDetails);
            return new AuthResponseDTO(token,user.getEmail(),
                    user.getRole().name(), "Registration successful");
    }

    // For login Request........

    public AuthResponseDTO login(LoginRequestDTO request) {

            // Authenticate credentials
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()
                    )
            );

            // Generate JWT token on success

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()->new RuntimeException("User not found"));
            String token = jwtUtil.generateToken(userDetails);

            return new AuthResponseDTO(token,user.getEmail(),
                    user.getRole().name(), "Login successful");
    }
}
