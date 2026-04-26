package com.example.workout.tracker.dto.workoutDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkOutExerciseDTO {
    private Long exerciseId;

    private int sets;
    private int reps;
    private double weight;
}
