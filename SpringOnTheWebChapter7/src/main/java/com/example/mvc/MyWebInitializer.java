package com.example.mvc;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//This is a continuation of the SpringOnTheWebChapter5
//All comments from SpringOnTheWebChapter6 have been deleted here

//Go to SpringWebConfig.java to start
public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringRootConfig.class };		
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
	
		return new Class[] { SpringWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	//Note: Import Java Servlet API 3.1.0 in Maven, by default it's 2.5
	//In order to specify how the MultipartResolver Bean will handle multipart messages, we need to configure it in our class that extends AbstractAnnotationConfigDispatcherServletInitializer
	//We do this by overriding the customizeRegistration method
	@Override
	protected void customizeRegistration(Dynamic registration) {
		
		//We do this by calling the setMultipartConfig method in the Dynamic class and providing it with a MultipartConfigElement
		//Configuration is done by setting the MultipartConfigElement properties
		//This MultipartConfigElement constructor takes a String that defines where the uploads will be stored temporarily (our controller will still need to handle permanent file storage)
		registration.setMultipartConfig(new MultipartConfigElement("C:/temp/"));
		
		//Alternatively, we can configure use a MultipartConfigElement constructor that defines the location, 
		//as well as specifies that each file should be no more than 2MB and the total request should be no more than 4MB
		//registration.setMultipartConfig(new MultipartConfigElement("/tmp/spittr/uploads", 2097152, 4194304, 0));
		
		//Next, we need to write a controller method that accepts multipart requests
		//Go to SpitterController
		
	};

	
	
}
