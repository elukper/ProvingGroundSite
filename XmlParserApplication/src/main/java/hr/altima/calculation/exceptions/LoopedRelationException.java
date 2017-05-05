package hr.altima.calculation.exceptions;

import java.util.ArrayList;
import java.util.List;

public class LoopedRelationException extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = -6166312895357514028L;

	private final List<String> errors = new ArrayList<>();

	public LoopedRelationException(final List<String> errors) {
		this.errors.addAll(errors);
	}

	public List<String> getErrors() {
		return errors;
	}

}
