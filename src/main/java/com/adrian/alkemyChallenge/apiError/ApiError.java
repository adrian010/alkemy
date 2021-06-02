package com.adrian.alkemyChallenge.apiError;

import org.springframework.http.HttpStatus;

public class ApiError {

	private String message;
	private int code;
	private HttpStatus status;
	
	
	public ApiError(String message, int code, HttpStatus status) {
		super();
		this.message = message;
		this.code = code;
		this.status = status;
	}


	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}


	public HttpStatus getStatus() {
		return status;
	}


	
	
	
	
}
