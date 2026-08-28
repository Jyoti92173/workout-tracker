package com.example.workout.tracker.service;


import com.example.workout.tracker.dto.auth.AuthResponseDTO;
import com.example.workout.tracker.dto.auth.LoginRequestDTO;
import com.example.workout.tracker.dto.auth.RegisterRequestDTO;

public interface AuthService {

    AuthResponseDTO register(RegisterRequestDTO request);
    AuthResponseDTO login(LoginRequestDTO loginRequest);
}
