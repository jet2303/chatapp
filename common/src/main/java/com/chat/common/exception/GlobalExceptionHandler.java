package com.chat.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
//	@ExceptionHandler(CustomErrorException.class)
//	public ResponseEntity<String> CustomErrorHandler(CustomErrorException ex){
//		return ResponseEntity.status(ex.getErrorCode().getStatusCode())
//							.body(ex.getErrorCode().getDescription());
//	}
	@ExceptionHandler(CustomErrorException.class)
	public ResponseEntity<ErrorResponse> CustomErrorHandler(CustomErrorException ex){
		return ResponseEntity.status(ex.getErrorCode().getStatusCode())
							.body(new ErrorResponse(ex.getErrorCode()));
	}
	
	
}

