package com.example.workout.tracker.dto.workoutDTOs;

import com.example.workout.tracker.dto.ExerciseResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class WorkOutResponseDTO {
    private Long workoutId;
    private String workoutName;
    private String comment;
    private LocalDateTime createdAt;
    private List<ExerciseResponseDTO> exercises;


}
