package com.mayuratech.api.exception;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.mayuratech.api.payload.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionClass extends ResponseEntityExceptionHandler{
	
	Logger demologger = LogManager.getLogger(GlobalExceptionClass.class.getName());
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,WebRequest webRequest) {
		demologger.error("Requested Resource Could not be found");
		ErrorDetails ed = new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(ed,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception,WebRequest webRequest) {
		demologger.error("Unexpected exception Occurred");
		ErrorDetails ed = new ErrorDetails(new Date(),exception.getMessage(),webRequest.getDescription(false));
		return new ResponseEntity<>(ed,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
