package com.send.email.template.data.otp;

import com.send.email.template.data.TemplateData;

public record EmailVerificationTemplateData(
		String name,
	    String otp,
	    int expiryMinutes,
	    String supportEmail) implements TemplateData {
}
