package com.fitnessapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessapp.dto.ExerciseDto;
import com.fitnessapp.response.Response;
import com.fitnessapp.service.ExerciseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/api/exercise")

public class ExerciseController {

	@Autowired
	ExerciseService exerciseService;
	
	@PostMapping(value = "/save")
	public Response save(@RequestBody ExerciseDto exerciseDto) {
		return new Response(HttpStatus.OK.value(), exerciseService.save(exerciseDto));
	}
	
	@GetMapping(value = "/list")
	public Response findAllCategories() {
		return new Response(HttpStatus.OK.value(), exerciseService.getAllExercises());
	}
	
	@GetMapping(value = "/{id}")
	public Response findById(@PathVariable Long id) {
		return new Response(HttpStatus.OK.value(), exerciseService.findById(id));
	}
	
}
