package com.send.email.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.send.email.dtos.EmailEvent;
import com.send.email.dtos.EmailRequest;
import com.send.email.enums.EmailTemplateTypes;
import com.send.email.factory.EmailTemplateFactory;
import com.send.email.service.TemplateService;
import com.send.email.template.data.TemplateData;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender javaMailSender ;
	private final Executor emailExecutor ;
	private final TemplateService templateService ;
	private final EmailTemplateFactory templateFactory ;

	public CompletableFuture<Void> sendEmailAsync(EmailRequest emailRequest) {
		return CompletableFuture.runAsync(() -> {
			try {
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(emailRequest.getTo()) ;
				if (emailRequest.getCc() != null) {
					message.setCc(emailRequest.getCc().toArray(new String[0])) ;
				}
				if (emailRequest.getBcc() != null) {
					message.setBcc(emailRequest.getBcc().toArray(new String[0])) ;
				}
				message.setSubject(emailRequest.getSubject()) ;
				message.setText(emailRequest.getBody()) ;
				javaMailSender.send(message) ;
			} catch (Exception ex) {
				throw new RuntimeException(ex) ;
			}
		}, emailExecutor) ;
	}

	public CompletableFuture<Void> sendEmailViaListenerAsync(EmailEvent emailEvent) {
		
		return CompletableFuture.runAsync(() -> sendEmail(emailEvent), emailExecutor)
				.exceptionally(ex -> {
					System.out.println("Email Failed : " + ex.getMessage()) ;
					return null ;
				});
	}
	
	private <T extends TemplateData> void sendEmail(EmailEvent emailEvent) {
	    try {
	        MimeMessage mimeMessage = javaMailSender.createMimeMessage() ;
	        MimeMessageHelper helper =  new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name()) ;

	        helper.setTo(emailEvent.recipient()) ;
	        
	        EmailTemplateTypes template = templateFactory.getTemplateByEventType(emailEvent.eventType(), emailEvent.template()) ;
	        TemplateData templateData = convertToTemplateData(emailEvent.variables(), template) ;
	        
	        if (!template.getTemplateDataClass().isInstance(templateData)) {
	            throw new IllegalArgumentException("Invalid template data for template: " + template);
	        }
	        
	        helper.setSubject(template.getDefaultSubject()) ;
	        helper.setText(templateService.render(template.getTemplateName(), convertToMap(templateData)), true) ;
	        
	        javaMailSender.send(mimeMessage);
	    } catch (MessagingException e) {
	        throw new RuntimeException("Email sending failed", e) ;
	    }
	}
	
	private Map<String, Object> convertToMap(Object object) {
		ObjectMapper mapper = new ObjectMapper() ;
	    return mapper.convertValue(object, new TypeReference<Map<String, Object>>() {}) ;
	}
	
	private TemplateData convertToTemplateData(
	        Map<String, Object> variables,
	        EmailTemplateTypes template
	) {
	    return new ObjectMapper().convertValue(
	            variables,
	            template.getTemplateDataClass()
	    ) ;
	}

}
