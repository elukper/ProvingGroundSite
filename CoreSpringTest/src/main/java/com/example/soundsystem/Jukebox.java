package com.example.soundsystem;

import com.example.music.discs.Artist;

public class Jukebox {
	
	Artist defaultArtist;
	
	public Jukebox(Artist defaultArtist) {
		this.defaultArtist = defaultArtist;
	}
	
	public void playDefault() {
		defaultArtist.play();
	}

}
