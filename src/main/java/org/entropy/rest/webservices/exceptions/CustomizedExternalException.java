package org.entropy.rest.webservices.exceptions;

import java.util.Date;

import org.entropy.rest.webservices.user.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomizedExternalException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllException(Exception ex,WebRequest request) {
		ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFpundException(UserNotFoundException ex,WebRequest request) {
		ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),"Body has Validation Error.",request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
}
