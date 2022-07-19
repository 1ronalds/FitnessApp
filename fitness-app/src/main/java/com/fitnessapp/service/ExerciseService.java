package com.fitnessapp.service;

import java.io.IOException;
import java.util.List;

import com.fitnessapp.entity.Exercise;
import org.springframework.web.multipart.MultipartFile;


public interface ExerciseService {

	List<Exercise> getAllExercises();
	
	Exercise save(MultipartFile multipartFile, Long categoryId, String name ,String description ) throws IOException;
	
	Exercise findById(Long id);
}
