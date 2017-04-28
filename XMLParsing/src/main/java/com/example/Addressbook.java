package com.example;

import java.util.ArrayList;
import java.util.List;

public class Addressbook {
	private String addressBookName;
	  private List   persons = new ArrayList<>();

	  public Addressbook() { }

	// -- manipulate the List of Person objects
	  public void addPerson(Person person) {
	    persons.add(person);
	  }

	  public List getPersons() {
	    return persons;
	  }

	// -- manipulate the name of the address book
	  public String getName() {
	    return addressBookName;
	  }

	  public void setName(String name) {
	    this.addressBookName = name;
	  }
}
