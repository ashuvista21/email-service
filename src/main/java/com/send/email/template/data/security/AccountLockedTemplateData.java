package com.send.email.template.data.security;

import com.send.email.template.data.TemplateData;

public record AccountLockedTemplateData(
		String name,
		String lockReason,
		String unlockUrl,
		String supportEmail) implements TemplateData {
}
