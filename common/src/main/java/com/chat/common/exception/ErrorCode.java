package com.chat.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
	
	SUCCESS(200,"성공"),
	FAILURE(404,"실패");
	
	
	private int statusCode;
	private String description;
}
