package com.example.workout.tracker.controller;

import com.example.workout.tracker.dto.exercise.ExerciseRequestDTO;
import com.example.workout.tracker.dto.exercise.ExerciseResponseDTO;
import com.example.workout.tracker.dto.exercise.ExerciseUpdateDTO;
import com.example.workout.tracker.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    // Create exercise
    @PostMapping
    public ResponseEntity<ExerciseResponseDTO> createExercise(@Valid @RequestBody ExerciseRequestDTO exerciseRequestDTO) {
        ExerciseResponseDTO createdExercise = exerciseService.createExercise(exerciseRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdExercise);
    }

    //  Get all exercises
    @GetMapping
    public  ResponseEntity<List<ExerciseResponseDTO>> getAllExercises(
            @AuthenticationPrincipal UserDetails userDetails) {

        String email = userDetails.getUsername();
        return ResponseEntity.ok(exerciseService.getAllExercises(email));
    }

    // Get exercise by ID
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseResponseDTO> getExerciseById(@PathVariable Long id) {
        ExerciseResponseDTO response = exerciseService.getExerciseById(id);

        return ResponseEntity.ok(response);
    }

    // Update exercise
    @PutMapping("/{id}")
    public ResponseEntity<ExerciseResponseDTO> updateExercise(@PathVariable Long id, @RequestBody ExerciseUpdateDTO request) {
        ExerciseResponseDTO updatedExercise = exerciseService.updateExercise(id, request);
        return ResponseEntity.ok(updatedExercise);
    }

    // Delete exercise
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }
}
