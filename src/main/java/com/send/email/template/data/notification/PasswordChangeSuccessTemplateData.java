package com.send.email.template.data.notification;

import com.send.email.template.data.TemplateData;

public record PasswordChangeSuccessTemplateData (
	String name,
	String changeTime,
	String supportEmail) implements TemplateData {} ;
