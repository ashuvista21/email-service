package com.send.email.template.data.security;

import com.send.email.template.data.TemplateData;

public record SuspiciousLoginTemplateData (
	String name,
	String loginTime,
	String location,
	String ipAddress,
	String deviceInfo,
	String secureAccountUrl,
	String supportEmail) implements TemplateData {
}
