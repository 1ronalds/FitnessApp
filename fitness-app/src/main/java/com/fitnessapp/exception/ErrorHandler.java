package com.fitnessapp.exception;

import java.util.Collections;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fitnessapp.response.Response;

import net.minidev.json.JSONObject;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseBody
	private Object returnRecordNotFoundException(Exception ex) {
		return new Response(420, ex.getMessage(), Collections.emptyList());
	}

	@ExceptionHandler(RecordNotFoundExceptionObject.class)
	@ResponseBody
	private Object returnRecordNotFoundExceptionObject(Exception ex) {
		return new Response(420, ex.getMessage(), new JSONObject());
	}
	
	@ExceptionHandler(InvalidRequestException.class)
	@ResponseBody
	private Object returnInvalidRequest(Exception ex) {
		return new Response(420, ex.getMessage(), new JSONObject());
	}
	
}
