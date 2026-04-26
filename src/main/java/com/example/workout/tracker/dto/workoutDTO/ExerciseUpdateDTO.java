package com.example.workout.tracker.dto.workoutDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseUpdateDTO {
    @NotNull(message = "Exercise id is required")
    private Long exerciseId;

    @Min(value = 1, message = "Sets must be at least 1")
    private Integer sets;

    @Min(value = 1, message = "Reps must be at least 1")
    private Integer reps;

    @Positive(message = "Weight must be positive")
    private Double weight;

    private List<WorkOutExerciseDTO> exercises;


}
