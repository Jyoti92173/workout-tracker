package model;

import jakarta.persistence.*;

@Entity
@Table(name = "exercises")

public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String category;      // e.g., cardio, strength, squat, stretching,balance
    private String muscleGroup;  // e.g., chest, legs,shoulder,back, arms
}
