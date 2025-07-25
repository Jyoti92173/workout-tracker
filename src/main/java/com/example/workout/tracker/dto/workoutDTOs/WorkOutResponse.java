package com.example.workout.tracker.dto.workoutDTOs;

import dto.WorkoutDTOs.WorkoutExerciseDTO;

import java.time.LocalDateTime;
import java.util.List;

public class WorkOutResponse {
    private Long workoutId;
    private String name;
    private LocalDateTime scheduledAt;
    private String comment;

    private List<WorkoutExerciseDTO> exercises;
}
