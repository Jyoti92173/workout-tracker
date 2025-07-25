package dto.WorkoutDTOs;

import java.time.LocalDateTime;
import java.util.List;

public class WorkoutRequest {
    private String name;
    private LocalDateTime scheduledAt;
    private String comment;

    private List<WorkoutExerciseDTO> exercises;
}
