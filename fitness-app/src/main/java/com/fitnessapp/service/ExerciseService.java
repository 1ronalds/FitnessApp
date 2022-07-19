package com.fitnessapp.service;

import java.util.List;

import com.fitnessapp.dto.ExerciseDto;
import com.fitnessapp.entity.Exercise;


public interface ExerciseService {

	List<Exercise> getAllExercises();
	
	Exercise save(ExerciseDto exerciseDto);
	
	Exercise findById(Long id);
}
