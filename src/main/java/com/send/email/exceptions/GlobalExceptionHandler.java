package com.send.email.exceptions;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.send.email.dtos.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// Handle validation errors (@Valid in DTOs)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException ex) {
        return buildResponse(ex.getBindingResult().getFieldErrors().stream()
    			.map(DefaultMessageSourceResolvable::getDefaultMessage)
    			.toList(), HttpStatus.BAD_REQUEST) ;
    }
    
    @ExceptionHandler(EmailException.class)
    public ResponseEntity<ApiResponse<Void>> handleEmailServiceException(EmailException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus()) ;
    }

    // Handle mail sending errors (SMTP issues, etc.)
    @ExceptionHandler(MailException.class)
    public ResponseEntity<ApiResponse<Void>> handleMailException(MailException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR) ;
    }

    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception ex) {
        return buildResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR) ;
    }
	
	private ResponseEntity<ApiResponse<Void>> buildResponse(String message, HttpStatus status) {
        return ResponseEntity.status(status)
            .body(ApiResponse.<Void>builder()
                .message(Arrays.asList(message))
                .success(false)
                .status(status)
                .build());
    }
    
    private ResponseEntity<ApiResponse<Void>> buildResponse(List<String> messages, HttpStatus status) {
    	if(messages.size() == 0)
    		return buildResponse("Validation Error", status) ;
        return ResponseEntity.status(status)
            .body(ApiResponse.<Void>builder()
                .message(messages)
                .success(false)
                .status(status)
                .build());
    }
	
}
