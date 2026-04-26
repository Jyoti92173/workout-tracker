package com.example.workout.tracker.controller;

import com.example.workout.tracker.dto.ExerciseDTO;
import com.example.workout.tracker.model.Exercise;
import com.example.workout.tracker.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Exercise> createExercise(@Valid @RequestBody ExerciseDTO exerciseDTO) {
        Exercise savedExercise = exerciseService.createExercise(exerciseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExercise);
    }

    //  Get all exercises
    @GetMapping
    public ResponseEntity<List<ExerciseDTO>> getAllExercises(UserDetails userDetails) {
        String email = userDetails.getUsername();
        return ResponseEntity.ok(exerciseService.getAllExercises(email));
    }

    // Get exercise by ID
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDTO> getExerciseById(@PathVariable Long id) {
        return exerciseService.getExerciseById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update exercise
    @PutMapping("/{id}")
    public ResponseEntity<ExerciseDTO> updateExercise(@PathVariable Long id, @RequestBody ExerciseDTO exerciseDTO) {
        ExerciseDTO updatedExercise = exerciseService.updateExercise(id, exerciseDTO);
        return ResponseEntity.ok(updatedExercise);
    }

    // Delete exercise
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }
}
