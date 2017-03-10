package com.example.soundsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class CDPlayer implements MediaPlayer{
	
	private CompactDisc compactDisc;
	
	@Autowired
	public void setCompactDisc(CompactDisc compactDisc) {
		this.compactDisc = compactDisc;
	}

	@Override
	public void play() {
		compactDisc.play();
		
	}

}
