package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class CoreSpringTestSettingPropertiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreSpringTestSettingPropertiesApplication.class, args);
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/jebeniservis.xml");
		
	}
}
