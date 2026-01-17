package com.send.email.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.send.email.dtos.EmailEvent;
import com.send.email.dtos.EmailRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender javaMailSender;
	private final Executor emailExecutor;

	public CompletableFuture<Void> sendEmailAsync(EmailRequest emailRequest) {
		return CompletableFuture.runAsync(() -> {
			try {
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(emailRequest.getTo());
				if (emailRequest.getCc() != null) {
					message.setCc(emailRequest.getCc().toArray(new String[0]));
				}
				if (emailRequest.getBcc() != null) {
					message.setBcc(emailRequest.getBcc().toArray(new String[0]));
				}
				message.setSubject(emailRequest.getSubject());
				message.setText(emailRequest.getBody());
				javaMailSender.send(message);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}, emailExecutor);
	}

	public CompletableFuture<Void> sendEmailViaListenerAsync(EmailEvent emailEvent) {
		String subject = resolveSubject(emailEvent) ;
		
		return CompletableFuture.runAsync(() -> {
			SimpleMailMessage message = new SimpleMailMessage() ;
			message.setTo(emailEvent.recipient()) ;
			message.setSubject(subject) ;
			message.setText("OTP to reset your password is " + emailEvent.variables().get("OTP").toString()) ;
			javaMailSender.send(message) ;
		}, emailExecutor)
				.exceptionally(ex -> {
					System.out.println("Email Failed : " + ex.getMessage()) ;
					return null ;
				});
	}
	
	private String resolveSubject(EmailEvent emailEvent) {
	    if ("OTP_SENT".equals(emailEvent.template())) {
	        return "Password Reset Request" ;
	    }
	    return "Email Event" ;
	}

}
