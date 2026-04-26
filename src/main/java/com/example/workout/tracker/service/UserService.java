//package com.example.workout.tracker.service;
//
//import com.example.workout.tracker.dto.UserDTO;
//import com.example.workout.tracker.model.Roles;
//import com.example.workout.tracker.model.User;
//import com.example.workout.tracker.repository.UserRepository;
//import io.jsonwebtoken.io.Base64Encoder;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class UserService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//
//    public UserDTO createUser(UserDTO userDTO) {
//        if (userRepository.existsByEmail(userDTO.getEmail())) {
//            throw new RuntimeException("Email already exists!");
//        }
//
//        User user = new User();
//        user.setUsername(userDTO.getUsername());
//        user.setEmail(userDTO.getEmail());
//        Base64Encoder passwordEncoder = null;
//        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // 🔒 encode password
//        user.setRole(Roles.valueOf("ROLE_USER")); // default role
//        userRepository.save(user);
//
//        return new UserDTO(user.getUsername(), user.getEmail(), null);
//    }
//
//    public UserDTO getUserByEmail(String email) {
//        try {
//            Optional<User> optionalUser = userRepository.findByEmail(email);
//
//            if (optionalUser.isPresent()) {
//                User user = optionalUser.get();
//
//                // Create and return the DTO with only name and email
//                return new UserDTO(user.getId(),user.getUsername(), user.getEmail(),user.getRole());
//            } else {
//                throw new RuntimeException("User not found with email: " + email);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Error retrieving user by email: " + e.getMessage(), e);
//        }
//    }
//
//    public UserDTO updateUser(String email, UserDTO updatedUser) {
//        try {
//            Optional<User> optionalUser = userRepository.findByEmail(email);
//
//            if (optionalUser.isPresent()) {
//                User user = optionalUser.get();
//
//                // Update only name (we usually don't allow updating email)
//                user.setUsername(updatedUser.getName());
//
//                // Save the updated user
//                User UserSave = userRepository.save(user);
//
//                // Return updated UserDTO
//                return new UserDTO( UserSave.getId(),UserSave.getUsername(),
//                        UserSave.getEmail(),UserSave.getRole());
//            } else {
//                throw new RuntimeException("User not found with email: " + email);
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException("Error updating user: " + e.getMessage(), e);
//        }
//    }
//    public void deleteUserByEmail(String email) {
//    }
//
//
//}


package com.example.workout.tracker.service;

import com.example.workout.tracker.dto.UserDTO;
import com.example.workout.tracker.model.Roles;
import com.example.workout.tracker.model.User;
import com.example.workout.tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Create a new user
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername()); // use username
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        // If admin provides a role, use it; otherwise default to ROLE_USER
        if (userDTO.getRole() != null) { //  use role
            user.setRole(userDTO.getRole());
        } else {
            user.setRole(Roles.ROLE_USER);
        }

        User savedUser = userRepository.save(user);

        return new UserDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }


    // Get user by email
    public UserDTO getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole());
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }

    // Update user (only username in this case)
    public UserDTO updateUser(String email, UserDTO updatedUser) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(updatedUser.getUsername()); //  fix mismatch (was getName())
            User savedUser = userRepository.save(user);
            return new UserDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getRole());
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }

    // Delete user
    public void deleteUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole()
                ))
                .collect(Collectors.toList());
    }

}
