package model;

import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
@Entity
@Table(name = "workouts")

public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime scheduledAt;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutExercise> workoutExercises = new ArrayList<>();
}
