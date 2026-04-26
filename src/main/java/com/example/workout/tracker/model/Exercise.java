package com.example.workout.tracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;// e.g., CARDIO, STRENGTH, ...

    @Enumerated(EnumType.STRING)
    private MuscleGroup muscleGroup;// e.g., CHEST, LEGS, ...

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
