package com.fitnessapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ExerciseDto {

	private long id;
	private long categoryId;
	private String name;
	private String description;
	
}
