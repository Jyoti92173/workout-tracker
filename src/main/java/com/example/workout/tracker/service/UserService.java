package com.example.workout.tracker.service;


import com.example.workout.tracker.dto.user.UserRegisterDTO;
import com.example.workout.tracker.dto.user.UserResponseDTO;
import com.example.workout.tracker.dto.user.UserUpdateDTO;

import java.util.List;

public interface UserService {


    UserResponseDTO createUser(UserRegisterDTO dto);

    UserResponseDTO createUserByAdmin(UserRegisterDTO dto);

    List<UserResponseDTO> getAllUsers();


    UserResponseDTO getUserByEmail(String email);

    UserResponseDTO updateUser(String email, UserUpdateDTO dto);

    UserResponseDTO getUserById(Long id);

    void deleteUser(Long id);






}