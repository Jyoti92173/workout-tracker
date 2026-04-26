package com.example.workout.tracker.repository;

import com.example.workout.tracker.model.Exercise;
import com.example.workout.tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByUser(User user);

}
