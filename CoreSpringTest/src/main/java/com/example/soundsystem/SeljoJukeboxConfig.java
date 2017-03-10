package com.example.soundsystem;

import org.springframework.context.annotation.Bean;

import com.example.music.discs.Artist;
import com.example.music.discs.RockArtist;
import com.example.music.discs.RockTrack;
import com.example.music.discs.Track;

public class SeljoJukeboxConfig {
	
	@Bean
	public Artist getDefaultArtist() {
		
		return new RockArtist("Severina", getDefaultTrack());
		
	}
	
	@Bean
	public Track getDefaultTrack() {
		return new RockTrack("Moja stikla");
	}

}
