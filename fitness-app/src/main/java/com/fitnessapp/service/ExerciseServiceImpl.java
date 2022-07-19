package com.fitnessapp.service;

import java.util.List;
import java.util.Optional;

import com.fitnessapp.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitnessapp.dto.ExerciseDto;
import com.fitnessapp.entity.Exercise;
import com.fitnessapp.exception.RecordNotFoundExceptionObject;
import com.fitnessapp.repository.ExerciseRepository;

@Service
public class ExerciseServiceImpl implements ExerciseService {
	
	@Autowired
	ExerciseRepository exerciseRepository;

	@Override
	public List<Exercise> getAllExercises() {
		return exerciseRepository.findAll();
	}

	@Override
	public Exercise save(ExerciseDto exerciseDto) {
		Exercise exercise = Exercise.builder().name(exerciseDto.getName()).description(exerciseDto.getDescription()).build();


		return exerciseRepository.save(exercise);
	}

	@Override
	public Exercise findById(Long id) {
		Optional<Exercise> optionalExercise = exerciseRepository.findById(id);
		if (optionalExercise.isEmpty())
			throw new RecordNotFoundExceptionObject("Task not find by id : " + id);
		return optionalExercise.get();
	}

}
