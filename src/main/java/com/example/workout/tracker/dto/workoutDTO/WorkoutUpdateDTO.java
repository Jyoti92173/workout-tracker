package com.example.workout.tracker.dto.workoutDTOs;

import java.util.List;

public class WorkoutUpdateDTO {
    private String workoutName;
    private String comment;

    private List<ExerciseUpdateDTO> exercises;
}
