package com.send.email.exceptions;

import org.springframework.http.HttpStatus;

public class UnsupportedEventType extends EmailException {
	
	private static final long serialVersionUID = 1L ;

	public UnsupportedEventType(String message) {
		super(message, HttpStatus.BAD_REQUEST) ;
	}

}
