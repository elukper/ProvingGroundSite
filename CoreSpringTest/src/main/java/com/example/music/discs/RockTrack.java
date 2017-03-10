package com.example.music.discs;

public class RockTrack implements Track{
	
	public RockTrack(String track){
		
		this.track=track;
		
	}
	
	private String track;


	@Override
	public String getTrack() {
		return track;
	}

}
