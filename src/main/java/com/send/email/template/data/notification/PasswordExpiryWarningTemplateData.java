package com.send.email.template.data.notification;

import com.send.email.template.data.TemplateData;

public record PasswordExpiryWarningTemplateData (
	String name,
	int daysRemaining,
	String changePasswordUrl,
	String supportEmail) implements TemplateData {} ;
