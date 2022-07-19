package com.fitnessapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessapp.dto.CategoryDto;
import com.fitnessapp.response.Response;
import com.fitnessapp.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/api/category")

public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response save(@RequestBody CategoryDto categoryDto) {
		return new Response(HttpStatus.OK.value(), categoryService.save(categoryDto));
	}
	
	@GetMapping(value = "/list")
	public Response findAllCategories() {
		return new Response(HttpStatus.OK.value(), categoryService.getAllCategories());
	}
	
	@GetMapping(value = "/{id}")
	public Response findById(@PathVariable Long id) {
		return new Response(HttpStatus.OK.value(), categoryService.findById(id));
	}
}
