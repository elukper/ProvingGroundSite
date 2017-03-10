package com.example.soundsystem;

import org.springframework.stereotype.Component;

//Create Class that implements CompactDisc interface
//Annotate it with @Component, so that's it's subject to component scan
@Component
public class Airbourne implements CompactDisc{
	
	private String title = "Never too loud";
	private String artist = "Airbourne";
	
	public void play() {
		System.out.println(artist+" - "+title);
	}

}
