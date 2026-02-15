package com.send.email.exceptions;

import org.springframework.http.HttpStatus;

public class EmailException extends RuntimeException {

	private static final long serialVersionUID = 1L ;
	private final HttpStatus status ;
	
	public EmailException(String message, HttpStatus status) {
		super(message) ;
		this.status = status ;
	}
	
	public EmailException(String message, HttpStatus status, Throwable cause) {
		super(message, cause) ;
		this.status = status ;
	}
	
	public HttpStatus getStatus() {
		return status ;
	}
}
