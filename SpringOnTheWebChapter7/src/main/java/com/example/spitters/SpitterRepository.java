package com.example.spitters;

public interface SpitterRepository {
	
	//Here we added a custom RuntimeException that we wish to map to an HTTP status. Go to DuplicateSpitterFoundException for mapping details
	//Go to DefaultSpitterRepository for custom implementation of the save method and conditions on when the exception is thrown
	void save(Spitter spitter) throws DuplicateSpitterFoundException;
	
	Spitter findByUsername(String userName); 

}
