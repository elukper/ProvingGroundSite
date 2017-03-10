package com.example.music.discs;

public class RockArtist implements Artist{
	
	private String artist;
	
	private Track track;
	
	public RockArtist(String artist, Track track){
		this.artist=artist;
		this.track=track;
	}
	
	public void setTrack(Track track) {
		
		this.track = track;
		
	}

	@Override
	public void play() {
		System.out.println(artist+" - "+track.getTrack());
		
	}
	
	

}
