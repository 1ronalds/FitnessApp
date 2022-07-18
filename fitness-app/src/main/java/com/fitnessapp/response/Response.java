package com.fitnessapp.response;

import lombok.Data;

@Data
public class Response {

	public int status;
	public String message = "Success";
	public Object exercises;

	public Response(int status, Object exercises) {
		this.status = status;
		this.exercises = exercises;
	}

	public Response(int status, String message, Object exercises) {
		this.message = message;
		this.status = status;
		this.exercises = exercises;
	}

}