package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.soundsystem.Jukebox;
import com.example.soundsystemconfig.JukeboxConfig;

@SpringBootApplication
public class CoreSpringTestAutowiringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreSpringTestAutowiringApplication.class, args);
		
		//In the main method, here first we must initiate component scanning, by creating an AnnotationConfigApplicationContext and passing it
		//the class that contains the  @ComponenetScan annotation with appropriate package definition
		ApplicationContext appCon = new AnnotationConfigApplicationContext(JukeboxConfig.class);
		
		//alternatively, this can be done via the xml
		//ApplicationContext appCon2 = new ClassPathXmlApplicationContext("jebeniservis.xml");
		
		
		//The Jukebox @Component is the "entry" into the wired bean structure. We fetch it by using the .getBean method
		Jukebox jukebox = appCon.getBean(Jukebox.class);
		jukebox.playSong();
		
		
		
	}
}
