package com.example.workout.tracker.dto.workoutDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkOutRequestDTO {
    private Long userId;
    private String workoutName;
    private String comment;
    private List<WorkOutExerciseDTO> exercises;


}
