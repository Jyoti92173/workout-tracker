package com.example.workout.tracker.service;

import com.example.workout.tracker.dto.workoutDTO.*;
import com.example.workout.tracker.exceptions.ResourceNotFoundException;
import com.example.workout.tracker.model.*;
import com.example.workout.tracker.repository.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkOutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    // ✅ CREATE WORKOUT
    @Transactional
    public WorkOutResponseDTO createWorkout(String email, @Valid WorkOutRequestDTO dto) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        validateWorkoutRequest(dto);

        Workout workout = new Workout();
        workout.setWorkoutName(dto.getWorkoutName());
        workout.setComment(dto.getComment());
        workout.setUser(user);

        // map exercises
        for (WorkOutExerciseDTO exDto : dto.getExercises()) {

            Exercise exercise = exerciseRepository.findById(exDto.getExerciseId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Exercise not found with id: " + exDto.getExerciseId()));

            WorkoutExercise we = new WorkoutExercise();
            we.setExercise(exercise);
            we.setSets(exDto.getSets());
            we.setReps(exDto.getReps());
            we.setWeight(exDto.getWeight());

            workout.addExercise(we); // ✅ helper method
        }

        workoutRepository.save(workout);

        return mapToResponseDTO(workout);
    }

    // ✅ GET ALL WORKOUTS
    public List<WorkOutResponseDTO> getWorkouts(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return workoutRepository.findByUser(user)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // ✅ GET WORKOUT BY ID
    public WorkOutResponseDTO getWorkoutById(Long id, String email) {

        Workout workout = getWorkoutByIdAndValidateUser(id, email);
        return mapToResponseDTO(workout);
    }

    // ✅ UPDATE WORKOUT EXERCISES
    @Transactional
    public WorkOutResponseDTO updateWorkoutExercises(Long id, ExerciseUpdateDTO request) {

        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workout not found"));

        // clear old exercises
        workout.getWorkoutExercises().clear();

        for (WorkOutExerciseDTO exDto : request.getExercises()) {

            Exercise exercise = exerciseRepository.findById(exDto.getExerciseId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Exercise not found with id: " + exDto.getExerciseId()));

            WorkoutExercise we = new WorkoutExercise();
            we.setExercise(exercise);
            we.setSets(exDto.getSets());
            we.setReps(exDto.getReps());
            we.setWeight(exDto.getWeight());

            workout.addExercise(we);
        }

        return mapToResponseDTO(workout);
    }

    // ✅ DELETE WORKOUT
    @Transactional
    public void deleteWorkout(Long id) {

        if (!workoutRepository.existsById(id)) {
            throw new ResourceNotFoundException("Workout not found");
        }

        workoutRepository.deleteById(id);
    }

    // ================= PRIVATE METHODS =================

    private Workout getWorkoutByIdAndValidateUser(Long id, String email) {

        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workout not found"));

        if (!workout.getUser().getEmail().equals(email)) {
            throw new IllegalArgumentException("Unauthorized access to workout");
        }

        return workout;
    }

    private void validateWorkoutRequest(@Valid WorkOutRequestDTO dto) {

        if (dto.getWorkoutName() == null || dto.getWorkoutName().isBlank()) {
            throw new IllegalArgumentException("Workout name is required");
        }

        if (dto.getExercises() == null || dto.getExercises().isEmpty()) {
            throw new IllegalArgumentException("At least one exercise is required");
        }
    }

    private WorkOutResponseDTO mapToResponseDTO(Workout workout) {

        WorkOutResponseDTO dto = new WorkOutResponseDTO();
        dto.setWorkoutId(workout.getId());
        dto.setWorkoutName(workout.getWorkoutName());
        dto.setComment(workout.getComment());

        List<WorkOutExerciseDTO> exercises = workout.getWorkoutExercises()
                .stream()
                .map(we -> {
                    WorkOutExerciseDTO ex = new WorkOutExerciseDTO();
                    ex.setExerciseId(we.getExercise().getId());
                    ex.setSets(we.getSets());
                    ex.setReps(we.getReps());
                    ex.setWeight(we.getWeight());
                    return ex;
                }).toList();

        dto.setExercises(exercises);

        return dto;
    }
}