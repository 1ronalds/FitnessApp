package com.fitnessapp.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EXERCISE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id;
	private long categoryId = 0;
	private String name;
	private String description;
	@Column(nullable = true, length = 64)
	private String image;
}
