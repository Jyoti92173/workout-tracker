package com.example.workout.tracker.dto.exercise;

import com.example.workout.tracker.model.MuscleGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String category;
    private MuscleGroup muscleGroup;

}
