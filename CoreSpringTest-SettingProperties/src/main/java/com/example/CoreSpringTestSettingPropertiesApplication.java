package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.soundsystem.BlankDisc;
import com.example.soundsystem.CompactDisc;

@SpringBootApplication
public class CoreSpringTestSettingPropertiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreSpringTestSettingPropertiesApplication.class, args);
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/jebeniservis.xml");
		
		CompactDisc cd = applicationContext.getBean(BlankDisc.class);
		cd.play();
		
	}
}
