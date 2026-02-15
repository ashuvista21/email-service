package com.send.email.service.impl;

import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.send.email.service.TemplateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(
		name = "email.template-engine",
		havingValue = "thymeleaf",
		matchIfMissing = true
)
public class ThymeleafTemplateService implements TemplateService {
	
	private final SpringTemplateEngine templateEngine ;

	@Override
	public String render(String templateName, Map<String, Object> variables) {
		
		Context context = new Context() ;
        
		context.setVariables(variables) ;

        return templateEngine.process(templateName, context) ;
	}
	
}
