package hr.altima.controllers;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class RuntimeExceptionGuard {

	Logger logger = Logger.getLogger(RuntimeExceptionGuard.class);

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> generalRuntimeException(final RuntimeException re) {
		return new ResponseEntity<String>("Unknown error occured, contact support or check logs", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
