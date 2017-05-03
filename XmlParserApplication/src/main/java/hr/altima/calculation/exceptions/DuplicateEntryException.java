package hr.altima.calculation.exceptions;

import java.util.ArrayList;
import java.util.List;

public class DuplicateEntryException extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = -4610513110650631250L;

	//Throws this exception when a duplicate entry is detected in XML input

	private final List<String> errors = new ArrayList<>();

	public DuplicateEntryException(final List<String> errors) {
		this.errors.addAll(errors);
	}

	public List<String> getErrors() {
		return errors;
	}



}
