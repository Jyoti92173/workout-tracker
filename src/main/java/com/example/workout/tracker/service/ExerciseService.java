package com.example.workout.tracker.service;


import com.example.workout.tracker.dto.exercise.ExerciseRequestDTO;
import com.example.workout.tracker.dto.exercise.ExerciseResponseDTO;
import com.example.workout.tracker.dto.exercise.ExerciseUpdateDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface ExerciseService  {


    ExerciseResponseDTO createExercise(@Valid ExerciseRequestDTO exerciseRequestDTO, String email);
    List<ExerciseResponseDTO> getAllExercises(String email);
    ExerciseResponseDTO getExerciseById(Long id);
    ExerciseResponseDTO updateExercise(Long id, ExerciseUpdateDTO request);
    void deleteExercise(Long id);
}
