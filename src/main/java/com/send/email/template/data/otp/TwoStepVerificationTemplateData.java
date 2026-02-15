package com.send.email.template.data.otp;

import com.send.email.template.data.TemplateData;

public record TwoStepVerificationTemplateData(
		String name,
	    String otp,
	    int expiryMinutes,
	    String supportEmail) implements TemplateData {
}
