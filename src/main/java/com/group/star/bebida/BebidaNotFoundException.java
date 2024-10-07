package com.group.star.bebida;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BebidaNotFoundException extends RuntimeException{

	public BebidaNotFoundException() {
		super("Bebida Not Found");
	}
}
