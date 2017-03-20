package com.example.spitters;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="Spitter already exists")
public class DuplicateSpitterFoundException extends RuntimeException{

}
