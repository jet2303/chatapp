package com.chat.common.exception;


import lombok.Getter;

@Getter
public class CustomErrorException extends RuntimeException{


	private static final long serialVersionUID = 1L;
	private final ErrorCode errorCode;
	
	public CustomErrorException(ErrorCode errorCode) {
		super(errorCode.getDescription());
		this.errorCode = errorCode;
	}
	
	
}
