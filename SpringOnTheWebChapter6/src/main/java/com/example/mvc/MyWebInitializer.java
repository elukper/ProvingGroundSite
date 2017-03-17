package com.example.mvc;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//This is a continuation of the SpringOnTheWebChapter5
//All comments from SpringOnTheWebChapter5 have been deleted here

//Go to form.jsp to start
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

	
	
}
