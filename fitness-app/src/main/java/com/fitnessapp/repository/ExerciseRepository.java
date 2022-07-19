package com.fitnessapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitnessapp.entity.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}
