package com.example.workout.tracker.controller;

import com.example.workout.tracker.dto.workoutDTOs.WorkOutExerciseDTO;
import com.example.workout.tracker.model.Workout;
import com.example.workout.tracker.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;
    @PostMapping("/create")
    public ResponseEntity<Workout> createWorkout(
            @RequestParam Long userId,
            @RequestParam String workoutName,
            @RequestParam(required = false) String comment,
            @RequestBody List<WorkOutExerciseDTO> exercises) {

        Workout workout = workoutService.createWorkout(userId, workoutName, comment, exercises);
        return ResponseEntity.status(HttpStatus.CREATED).body(workout);
    }

    @GetMapping("/AllWorkout")
    public ResponseEntity<List<Workout>> getAllWorkouts() {
        List<Workout> workouts = workoutService.getAllWorkouts();
        return ResponseEntity.ok(workouts);
    }

    // Get workout by ID
    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable Long id) {
        Workout workout = workoutService.getWorkoutById(id);
        if (workout != null) {
            return ResponseEntity.ok(workout);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete workout
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
        workoutService.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }
}
