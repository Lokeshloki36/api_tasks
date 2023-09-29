package com.mayuratech.api.exception;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	Logger demologger = LogManager.getLogger(ApplicationExceptionHandler.class.getName());
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidException(MethodArgumentNotValidException exception){
		demologger.error("Unexpected exception from Dto class");
		Map<String,String> map = new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(
				error->{map.put(error.getField(), error.getDefaultMessage());
				});
		return map;
	}
}
