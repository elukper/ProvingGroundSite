package com.example.spitters;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Spitter {
	
	//The private parameters below have been marked with annotations. These are validations of the Java Validation API
	//Basically, it's used in the SpitterController when mapping input form data to the Spitter
	//If any validations will not be met, data is logged in the Errors (also SpitterController)
	//Check Spring into Action page 160 for full list of annotation validators
	
	@NotNull
	@Size(min=3,max=16)
	private String firstName;
	
	@NotNull
	@Size(min=3,max=16)
	private String lastName;
	
	@NotNull
	@Size(min=3,max=16)
	private String username;
	
	@NotNull
	@Size(min=3,max=30)
	private String password;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
