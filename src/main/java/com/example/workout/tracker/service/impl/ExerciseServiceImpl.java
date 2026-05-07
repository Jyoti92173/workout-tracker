package com.example.workout.tracker.service.impl;

import com.example.workout.tracker.dto.exercise.ExerciseRequestDTO;
import com.example.workout.tracker.dto.exercise.ExerciseResponseDTO;
import com.example.workout.tracker.dto.exercise.ExerciseUpdateDTO;
import com.example.workout.tracker.exceptions.ResourceNotFoundException;
import com.example.workout.tracker.entity.Category;
import com.example.workout.tracker.entity.Exercise;
import com.example.workout.tracker.entity.User;
import com.example.workout.tracker.repository.ExerciseRepository;
import com.example.workout.tracker.repository.UserRepository;
import com.example.workout.tracker.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {


    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;


    public ExerciseResponseDTO createExercise(ExerciseRequestDTO exerciseDTO, String email){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Exercise exercise = new Exercise();
        exercise.setName(exerciseDTO.getName());
        exercise.setDescription(exerciseDTO.getDescription());
        exercise.setCategory(exerciseDTO.getCategory());
        exercise.setMuscleGroup(exerciseDTO.getMuscleGroup());

        exercise.setUser(user);

        Exercise savedExercise = exerciseRepository.save(exercise);

        return mapToResponseDTO(savedExercise);
    }

    public List<ExerciseResponseDTO> getAllExercises(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Exercise> exercises = exerciseRepository.findByUser(user);

        return exercises.stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public ExerciseResponseDTO getExerciseById(Long id) {

        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + id));

        return mapToResponseDTO(exercise);
    }

    public ExerciseResponseDTO updateExercise(Long id, ExerciseUpdateDTO request) {

        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + id));

        // Optional updates (safe handling)
        if (request.getName() != null) {
            exercise.setName(request.getName());
        }

        if (request.getDescription() != null) {
            exercise.setDescription(request.getDescription());
        }

        if (request.getCategory() != null) {
            exercise.setCategory(Category.valueOf(request.getCategory()));
        }

        if (request.getMuscleGroup() != null) {
            exercise.setMuscleGroup(request.getMuscleGroup());
        }

        Exercise updated = exerciseRepository.save(exercise);

        return mapToResponseDTO(updated);
    }

    public void deleteExercise(Long id) {
        if (!exerciseRepository.existsById(id)) {
            throw new IllegalArgumentException("Exercise not found with id: " + id);
        }
        exerciseRepository.deleteById(id);

    }


    private ExerciseResponseDTO mapToResponseDTO(Exercise exercise) {

        ExerciseResponseDTO dto = new ExerciseResponseDTO();
        dto.setId(exercise.getId());
        dto.setName(exercise.getName());
        dto.setDescription(exercise.getDescription());
        dto.setCategory(String.valueOf(exercise.getCategory()));
        dto.setMuscleGroup(exercise.getMuscleGroup());

        return dto;
    }
}
