package com.example.workout.tracker.service;

import com.example.workout.tracker.dto.ExerciseDTO;
import com.example.workout.tracker.model.Exercise;
import com.example.workout.tracker.repository.ExerciseRepository;
import io.micrometer.observation.ObservationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public Exercise createExercise(ExerciseDTO exerciseDTO){
            Exercise exercise = new Exercise();
            exercise.setName(exerciseDTO.getName());
            exercise.setDescription(exerciseDTO.getDescription());
            exercise.setCategory(exerciseDTO.getCategory());
            exercise.setMuscleGroup(exerciseDTO.getMuscleGroup());

            return exerciseRepository.save(exercise);

    }

    public List<ExerciseDTO> getAllExercises(String email) {
        List<Exercise> exercises = exerciseRepository.findAll();

        return exercises.stream()
                .map(exercise -> new ExerciseDTO(
                        exercise.getId(),
                        exercise.getName(),
                        exercise.getDescription(),
                        exercise.getCategory(),
                        exercise.getMuscleGroup()
                ))
                .collect(Collectors.toList());
    }

    public ObservationFilter getExerciseById(Long id) {
        
    }

    public ExerciseDTO updateExercise(Long id, ExerciseDTO exerciseDTO) {
        return null;
    }
}
