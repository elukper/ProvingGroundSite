package com.example.spitters;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DefaultSpitterRepository implements SpitterRepository{
	
	Map<String,Spitter> spitterByUsername = new HashMap<String, Spitter>();
	
	@Override
	public void save(Spitter spitter) {
		if(spitterByUsername.containsKey(spitter.getUsername()))
			throw new DuplicateSpitterFoundException();
		
		spitterByUsername.put(spitter.getUsername(), spitter);
		
	}

	@Override
	public Spitter findByUsername(String userName) {
		return spitterByUsername.get(userName);
	}

}
