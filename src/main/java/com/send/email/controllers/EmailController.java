package com.send.email.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.send.email.dtos.ApiResponse;
import com.send.email.dtos.EmailRequest;
import com.send.email.service.EmailService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/email")
public class EmailController {
	
	@Autowired
	private EmailService emailService ;
	
	@PostMapping("/send")
	public ResponseEntity<ApiResponse<Void>> sendEmail(@Valid @RequestBody EmailRequest request) {
		emailService.sendEmailAsync(request) ;
		return ResponseEntity.ok(ApiResponse.<Void>builder()
				.message(Arrays.asList("Email sent successfully"))
				.status(HttpStatus.OK)
				.success(true)
				.data(null)
				.build()) ;
	}
}
