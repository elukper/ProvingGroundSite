package com.example.soundsystem;

import java.util.List;

//A class that will be defined as a bean in xml is created with private property variables and getter and setter methods
public class BlankDisc implements CompactDisc{
	
	private String title;
	private String artist;
	private List<String> tracks;
	
	

	public void setTitle(String title) {
		this.title = title;
	}



	public void setArtist(String artist) {
		this.artist = artist;
	}



	public void setTracks(List<String> tracks) {
		this.tracks = tracks;
	}



	@Override
	public void play() {
		
		System.out.println("Playing "+title+" by "+artist);
		for(String track : tracks) {
			System.out.println("-Track: "+track);
		}
		
	}
	
	
	
	

}
