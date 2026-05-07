package com.example.workout.tracker.service;


import com.example.workout.tracker.dto.workoutDTO.ExerciseUpdateDTO;
import com.example.workout.tracker.dto.workoutDTO.WorkOutRequestDTO;
import com.example.workout.tracker.dto.workoutDTO.WorkOutResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface WorkOutService {

    WorkOutResponseDTO createWorkout(String username, @Valid WorkOutRequestDTO dto);

    List<WorkOutResponseDTO> getWorkouts(String username);

    WorkOutResponseDTO getWorkoutById(Long id, String username);

    WorkOutResponseDTO updateWorkoutExercises(Long id, ExerciseUpdateDTO request);

    void deleteWorkout(Long id);
}