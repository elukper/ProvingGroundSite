package com.example;

import java.util.ArrayList;
import java.util.List;

public class Customer2 {
	
	private List<String> names = new ArrayList<>();

	public List<String> getName() {
		return names;
	}

	public void setName(List<String> name) {
		this.names = name;
	}
	
	public void addName(String name) {
		this.names.add(name);
	}
	
	

}
