package com.example.spittles;

import java.util.List;

public interface SpittleRepository {
	
	List<Spittle> getSpittles();
	
	List<Spittle> getSpittles(int count);
	
	Spittle getSpittleById(long id);

}
