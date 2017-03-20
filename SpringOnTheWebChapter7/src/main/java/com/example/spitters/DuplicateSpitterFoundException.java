package com.example.spitters;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//The @ResponseStatus decides which HttpStatus will be displayed(406 this time), and which message to display
//When the exception occurs, the user is rediredted automatically to a 406 webpage displaying this message
//Since this extends a RuntimeException, we don't need to catch it and the code won't break (the user is simply redirected to a 406 webpage)
//But if we didn't use a @ResponseStatus and we extended a RuntimeException, then the code would break without a try-catch block
//Note that since this is a RuntimeException, a try-catch block isn't necessary
//If we wanted to redirect the user to a different webpage or something similar other that a generated Http error, that we would skip the @ResponseStatus and would extend a regular Exception
@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="Spitter already exists")
public class DuplicateSpitterFoundException extends RuntimeException{

}
