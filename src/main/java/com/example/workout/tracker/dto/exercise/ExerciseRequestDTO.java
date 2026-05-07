package com.example.workout.tracker.dto.exercise;

import com.example.workout.tracker.entity.Category;
import com.example.workout.tracker.entity.MuscleGroup;
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
    private Category category;

    @NotNull
    private MuscleGroup muscleGroup;
}
