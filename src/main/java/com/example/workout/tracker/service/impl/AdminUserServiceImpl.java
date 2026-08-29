package com.example.workout.tracker.service.impl;

import com.example.workout.tracker.dto.user.UserRegisterDTO;
import com.example.workout.tracker.dto.user.UserResponseDTO;
import com.example.workout.tracker.service.AdminUserService;
import com.example.workout.tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final UserService userService;

    @Override
    public UserResponseDTO createUserByAdmin(UserRegisterDTO dto) {
        return userService.createUserByAdmin(dto);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
