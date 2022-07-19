package com.fitnessapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitnessapp.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
