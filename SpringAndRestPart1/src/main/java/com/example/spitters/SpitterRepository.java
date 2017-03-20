package com.example.spitters;

public interface SpitterRepository {
	
	void save(Spitter spitter) throws DuplicateSpitterFoundException;
	
	Spitter findByUsername(String userName); 

}
