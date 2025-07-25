package com.example.workout.tracker.repository;

import com.example.workout.tracker.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
