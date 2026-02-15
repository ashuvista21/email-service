package com.send.email.enums;

import com.send.email.template.data.TemplateData;
import com.send.email.template.data.security.AccountLockedTemplateData;
import com.send.email.template.data.security.SuspiciousLoginTemplateData;

public enum SecurityTemplateTypes implements EmailTemplateTypes {
	SUSPICIOUS_LOGIN("security/suspicious-login", "Suspicious Activity Detected", SuspiciousLoginTemplateData.class),
    ACCOUNT_LOCKED("security/account-locked", "Account Access Update", AccountLockedTemplateData.class) ;
	
	private final String templateName ;
	private final String defaultSubject ;
	private final Class<? extends TemplateData> templateDataClass ;

	SecurityTemplateTypes(String templateName, String defaultSubject, Class<? extends TemplateData> templateDataClass) {
        this.templateName = templateName ;
        this.defaultSubject = defaultSubject ;
        this.templateDataClass = templateDataClass ;
    }
	
	@Override
    public String getTemplateName() {
        return templateName ;
    }
	
	@Override
    public String getDefaultSubject() {
        return defaultSubject;
    }

	@Override
	public Class<? extends TemplateData> getTemplateDataClass() {
		return templateDataClass ;
	}
}
