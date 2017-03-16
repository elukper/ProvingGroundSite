package com.example.mvc;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//Initializer class. We use this instead of the web.xml file
public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
//Note: check the pom.xml <finalName> as well. The <finalName> parameter decides how the user can access the service. E.g localhost:8080/<finalName>/spittles

	//Returns a list of @Configuration classes that perform initial bean configuration and componenet scan for beans that are NOT web-related
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringRootConfig.class };		
	}

	//Returns a list of @Configuration classes that perform initial bean configuration and componenet scan for beans that ARE web-related
	@Override
	protected Class<?>[] getServletConfigClasses() {
	
		return new Class[] { SpringWebConfig.class };
	}

	//Provides servlet mappings
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
