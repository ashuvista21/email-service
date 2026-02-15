package com.send.email.enums;

import com.send.email.template.data.TemplateData;

public interface EmailTemplateTypes {
	String getTemplateName() ;
	String getDefaultSubject() ;
	public Class<? extends TemplateData> getTemplateDataClass() ;
}