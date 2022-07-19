package com.fitnessapp.service;

import java.util.List;

import com.fitnessapp.dto.CategoryDto;
import com.fitnessapp.entity.Category;

public interface CategoryService {
	
	List<Category> getAllCategories();
	
	Category save(CategoryDto categoryDto);
	
	Category findById(Long id);

}
