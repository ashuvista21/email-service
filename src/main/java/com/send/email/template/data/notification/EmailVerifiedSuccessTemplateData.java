package com.send.email.template.data.notification;

import com.send.email.template.data.TemplateData;

public record EmailVerifiedSuccessTemplateData(
		String name,
		String loginUrl,
		String supportEmail) implements TemplateData {
}
