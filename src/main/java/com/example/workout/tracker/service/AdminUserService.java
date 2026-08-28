package com.example.workout.tracker.service;

import com.example.workout.tracker.dto.user.UserRegisterDTO;
import com.example.workout.tracker.dto.user.UserResponseDTO;

import java.util.List;

public interface AdminUserService {

    UserResponseDTO createUserByAdmin(UserRegisterDTO dto);
    List<UserResponseDTO> getAllUsers();
}
