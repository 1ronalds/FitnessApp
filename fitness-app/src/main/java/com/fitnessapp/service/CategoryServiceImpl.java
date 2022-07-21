package com.fitnessapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitnessapp.dto.CategoryDto;
import com.fitnessapp.entity.Category;
import com.fitnessapp.exception.RecordNotFoundExceptionObject;
import com.fitnessapp.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();

    }

    @Override
    public Category save(CategoryDto categoryDto) {
        Category category = Category.builder().name(categoryDto.getName()).description(categoryDto.getDescription()).build();
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            throw new RecordNotFoundExceptionObject("Category not find by id : " + id);
        return optionalCategory.get();
    }
}
