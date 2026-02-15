package com.send.email.enums;

import com.send.email.template.data.TemplateData;
import com.send.email.template.data.notification.EmailVerifiedSuccessTemplateData;
import com.send.email.template.data.notification.PasswordChangeSuccessTemplateData;
import com.send.email.template.data.notification.PasswordExpiryWarningTemplateData;
import com.send.email.template.data.notification.UserRegistrationSuccessTemplateData;

public enum NotificationTemplateTypes implements EmailTemplateTypes {
	REGISTRATION_SUCCESS("notification/user-registration-success", "Welcome to our Platform", UserRegistrationSuccessTemplateData.class),
    PASSWORD_CHANGE_SUCCESS("notification/password-change-success", "Password Change Acknowledgement", PasswordChangeSuccessTemplateData.class),
    EMAIL_VERIFIED_SUCCESS("notification/email-verified-success", "Email Verification Acknowledgement", EmailVerifiedSuccessTemplateData.class),
    PASSWORD_EXPIRY_WARNING("notification/password-expiry-warning", "Credentials Expiry Notification", PasswordExpiryWarningTemplateData.class) ;
    
    private final String templateName ;
    private final String defaultSubject ;
    private final Class<? extends TemplateData> templateDataClass ;
    

	NotificationTemplateTypes(String templateName, String defaultSubject,Class<? extends TemplateData> templateDataClass) {
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
