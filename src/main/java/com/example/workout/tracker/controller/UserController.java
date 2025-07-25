package com.example.workout.tracker.controller;

import dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    // ✅ Get current user info
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<String>> getCurrentUser(@AuthenticationPrincipal String email) {
        try {
            UserDTO userDTO = userService.getUserByEmail(email);
            return ResponseEntity.ok(new ApiResponse<>(true, "User fetched successfully", userDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, "Failed to fetch user: " + e.getMessage(), null));
        }
    }

    // ✅ Update current user info
    @PutMapping("/me")
    public ResponseEntity<ApiResponse<String>> updateCurrentUser(
            @AuthenticationPrincipal String email,
            @RequestBody UserDTO updatedUser
    ) {
        try {
            UserDTO updated = userService.updateUser(email, updatedUser);
            return ResponseEntity.ok(new ApiResponse<>(true, "User updated successfully", updated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, "Update failed: " + e.getMessage(), null));
        }
    }

    // ✅ Delete current user
    @DeleteMapping("/me")
    public ResponseEntity<ApiResponse<String>> deleteUser(@AuthenticationPrincipal String email) {
        try {
            userService.deleteUserByEmail(email);
            return ResponseEntity.ok(new ApiResponse<>(true, "User deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, "Delete failed: " + e.getMessage(), null));
        }
    }
}
