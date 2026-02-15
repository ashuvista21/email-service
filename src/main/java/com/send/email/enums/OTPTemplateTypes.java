package com.send.email.enums;

import com.send.email.template.data.TemplateData;
import com.send.email.template.data.otp.EmailVerificationTemplateData;
import com.send.email.template.data.otp.PasswordChangeTemplateData;
import com.send.email.template.data.otp.PasswordResetTemplateData;
import com.send.email.template.data.otp.TwoStepVerificationTemplateData;

public enum OTPTemplateTypes implements EmailTemplateTypes {
	OTP_EMAIL_VERIFICATION("otp/email-verification", "Email Verification Code", EmailVerificationTemplateData.class),
    OTP_PASSWORD_RESET("otp/password-reset", "Password Reset Code", PasswordResetTemplateData.class),
    OTP_PASSWORD_CHANGE("otp/password-change", "Password Change Request Code", PasswordChangeTemplateData.class),
    OTP_TWO_FACTOR("otp/two-step-verification", "Two factor Authentication Code", TwoStepVerificationTemplateData.class) ;

    private final String templateName ;
    private final String defaultSubject ;
    private final Class<? extends TemplateData> templateDataClass ;

    OTPTemplateTypes(String templateName, String defaultSubject, Class<? extends TemplateData> templateDataClass) {
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
        return templateDataClass;
    }
}
