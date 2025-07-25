package dto.WorkoutDTOs;

import java.time.LocalDateTime;
import java.util.List;

public class WorkoutResponse {
    private Long workoutId;
    private String name;
    private LocalDateTime scheduledAt;
    private String comment;

    private List<WorkoutExerciseDTO> exercises;
}
