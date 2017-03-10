package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.example.music.discs.Artist;
import com.example.music.discs.RockArtist;
import com.example.soundsystem.Jukebox;
import com.example.soundsystem.JukeboxConfig;

@SpringBootApplication
public class CoreSpringTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreSpringTestApplication.class, args);
		
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(JukeboxConfig.class);
		
		ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext("/soundsystem/SeljoJukeboxConfig.xml");
		
		
		Jukebox jukebox = new Jukebox(appContext.getBean(RockArtist.class));
		
		appContext.close();
		
		jukebox.playDefault();
		
		Jukebox jukebox2 = new Jukebox(xmlContext.getBean(RockArtist.class));
		
		xmlContext.close();
		
		jukebox2.playDefault();
		
	}
}
