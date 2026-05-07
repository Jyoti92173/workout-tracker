package com.example.workout.tracker.controller;

import com.example.workout.tracker.dto.user.UserRegisterDTO;
import com.example.workout.tracker.dto.user.UserResponseDTO;
import com.example.workout.tracker.service.AdminUserService;
import com.example.workout.tracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserService userService;
    private final AdminUserService adminUserService;

    // Create a new user (Admin only)
    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> createUser(
            @RequestBody @Valid UserRegisterDTO dto) {

        UserResponseDTO createdUser = adminUserService.createUserByAdmin(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // List all users (Admin only)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {

        List<UserResponseDTO> users = adminUserService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}