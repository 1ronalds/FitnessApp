package com.fitnessapp.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fitnessapp.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitnessapp.entity.Exercise;
import com.fitnessapp.exception.RecordNotFoundExceptionObject;
import com.fitnessapp.repository.ExerciseRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExerciseServiceImpl implements ExerciseService {

	@Autowired
	ExerciseRepository exerciseRepository;

	@Override
	public List<Exercise> getAllExercises() {
		return exerciseRepository.findAll();
	}

	@Override
	public Exercise save(MultipartFile multipartFile, Long categoryId, String name, String description) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String uploadDir = "src/main/resources/images/";
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		return exerciseRepository.save(Exercise.builder()
				.name(name)
				.description(description)
				.image(uploadDir + fileName)
				.categoryId(categoryId).build());
	}

	@Override
	public Exercise findById(Long id) {
		Optional<Exercise> optionalExercise = exerciseRepository.findById(id);
		if (optionalExercise.isEmpty())
			throw new RecordNotFoundExceptionObject("Task not find by id : " + id);
		return optionalExercise.get();
	}

	@Override
	public void deleteById(Long id){
		final var exist =exerciseRepository.existsById(id);
		if (!exist) {
			throw new RecordNotFoundExceptionObject("Task not find by id : " + id);
		}
		else {
			exerciseRepository.deleteById(id);
		}
	}
}
