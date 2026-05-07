package com.example.workout.tracker.repository;

import com.example.workout.tracker.entity.User;
import com.example.workout.tracker.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout,Long> {

    List<Workout> findByUser(User user);

}
