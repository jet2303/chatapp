package com.chat.common.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
	        BindingResult result = ex.getBindingResult();
	        StringBuilder errMessage = new StringBuilder();

	        for (FieldError error : result.getFieldErrors()) {
	            errMessage.append("[")
	                    .append(error.getField())
	                    .append("] ")
	                    .append(":")
	                    .append(error.getDefaultMessage());
	        }

	        return new ResponseEntity<>(errMessage , HttpStatus.BAD_REQUEST);
	}

}
