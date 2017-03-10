package com.example.soundsystem;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//Create class that will use an @Autowired bean
//You must mark it as @Component, so it can be subject to component scan
@Component
public class Jukebox {
	
	//Create an @Autowired ref to a bean. This will fetch the @Autowired @Component Airbourne
	@Autowired
	private CompactDisc cd;
	
//	@Test
	public void playSong() {
		
		cd.play();
		
	}

}
