package com.example.workout.tracker.dto.exercise;

import com.example.workout.tracker.model.MuscleGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseRequestDTO {
    @NotBlank
    private String name;

    private String description;

    @NotNull
    private String category;

    @NotNull
    private MuscleGroup muscleGroup;
}
