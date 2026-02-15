package com.send.email.template.data.otp;

import com.send.email.template.data.TemplateData;

public record PasswordResetTemplateData(
		String name,
	    String otp,
	    int expiryMinutes) implements TemplateData {
} ;
