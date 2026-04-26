package com.example.workout.tracker.service;

import com.example.workout.tracker.dto.user.UserRegisterDTO;
import com.example.workout.tracker.dto.user.UserResponseDTO;
import com.example.workout.tracker.dto.user.UserUpdateDTO;
import com.example.workout.tracker.exceptions.ResourceNotFoundException;
import com.example.workout.tracker.model.Roles;
import com.example.workout.tracker.model.User;
import com.example.workout.tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ✅ CREATE USER
    @Transactional
    public UserResponseDTO createUser(UserRegisterDTO dto) {

        // check duplicate email
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Roles.ROLE_USER); // can replace with enum later

        userRepository.save(user);

        return mapToResponseDTO(user);
    }

    // ✅ UPDATE USER (by email)
    @Transactional
    public UserResponseDTO updateUser(String email, UserUpdateDTO dto) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // validation: nothing to update
        if (dto.getUsername() == null && dto.getPassword() == null) {
            throw new IllegalArgumentException("No fields provided for update");
        }

        if (dto.getUsername() != null && !dto.getUsername().isBlank()) {
            user.setUsername(dto.getUsername());
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        if(dto.getEmail()!=null && !dto.getEmail().isBlank()){
            if(userRepository.existsByEmail(dto.getEmail())){
                throw new IllegalArgumentException("Email already in use");
            }
            user.setEmail(dto.getEmail());
        }

        userRepository.save(user);

        return mapToResponseDTO(user);
    }

    // ✅ GET ALL USERS
    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // ✅ GET USER BY EMAIL
    public UserResponseDTO getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return mapToResponseDTO(user);
    }

    // ✅ GET USER BY ID
    public UserResponseDTO getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + id));

        return mapToResponseDTO(user);
    }

    // ✅ DELETE USER
    @Transactional
    public void deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        userRepository.deleteById(id);
    }

    // ✅ MAPPER
    private UserResponseDTO mapToResponseDTO(User user) {

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());

        return dto;
    }
}