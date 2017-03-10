package com.example.soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.music.discs.Artist;
import com.example.music.discs.RockArtist;
import com.example.music.discs.RockTrack;
import com.example.music.discs.Track;

@Configuration
public class JukeboxConfig {
	
	@Bean
	public Artist getArtist() {
		return new RockArtist("Airbourne",getDefaultTrack());
	}
	
	@Bean
	public Track getDefaultTrack() {
		return new RockTrack("Never Too Loud");
	}

}
