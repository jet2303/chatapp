package com.chat.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

	private int statusCode;
	private String description; 
	
	public ErrorResponse(ErrorCode errorCode) {
		this.statusCode = errorCode.getStatusCode();
		this.description = errorCode.getDescription();
	}
	
}
