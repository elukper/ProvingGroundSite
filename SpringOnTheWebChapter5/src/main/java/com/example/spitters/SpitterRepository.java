package com.example.spitters;

public interface SpitterRepository {
	
	void save(Spitter spitter);
	
	Spitter findByUsername(String userName); 

}
