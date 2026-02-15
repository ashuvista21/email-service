package com.send.email.factory;

import org.springframework.stereotype.Component;

import com.send.email.enums.EmailTemplateTypes;
import com.send.email.enums.EventTypes;
import com.send.email.enums.NotificationTemplateTypes;
import com.send.email.enums.OTPTemplateTypes;
import com.send.email.enums.SecurityTemplateTypes;
import com.send.email.exceptions.UnsupportedEventType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailTemplateFactory {
	
	public EmailTemplateTypes getTemplateByEventType(String eventType, String templateName) {
		return switch(EventTypes.valueOf(eventType)) {
			case OTP -> OTPTemplateTypes.valueOf(templateName) ;
			case NOTIFICATION -> NotificationTemplateTypes.valueOf(templateName) ;
			case SECURITY -> SecurityTemplateTypes.valueOf(templateName) ;
			default -> throw new UnsupportedEventType(eventType + " type email events are not supported") ;
		} ;
	}
}
