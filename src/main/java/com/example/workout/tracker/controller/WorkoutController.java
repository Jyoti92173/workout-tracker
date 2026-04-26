package com.example.workout.tracker.controller;

import com.example.workout.tracker.dto.workoutDTO.ExerciseUpdateDTO;
import com.example.workout.tracker.dto.workoutDTO.WorkOutRequestDTO;
import com.example.workout.tracker.dto.workoutDTO.WorkOutResponseDTO;
import com.example.workout.tracker.service.WorkOutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkOutService workOutService;

    @PostMapping("/create")
    public ResponseEntity<WorkOutResponseDTO> createWorkout(@AuthenticationPrincipal UserDetails userDetails,
                                                            @RequestBody @Valid WorkOutRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(workOutService.createWorkout(userDetails.getUsername(), dto));
    }

    // Get all workouts............
    @GetMapping
    public ResponseEntity<List<WorkOutResponseDTO>> getWorkouts(
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(workOutService.getWorkouts(userDetails.getUsername()));
    }

    // Get workout by ID
    @GetMapping("/{id}")
    public ResponseEntity<WorkOutResponseDTO> getWorkoutById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(workOutService.getWorkoutById(id, userDetails.getUsername()));
    }
    // Update exercises
    @PutMapping("/{id}/exercises")
    public ResponseEntity<WorkOutResponseDTO> updateWorkout(
            @PathVariable Long id,
            @RequestBody ExerciseUpdateDTO request) {

        return ResponseEntity.ok(workOutService.updateWorkoutExercises(id, request));
    }


    // Delete workout
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
        workOutService.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }


}
