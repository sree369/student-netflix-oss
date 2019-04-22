package org.sree.studentnetflixoss.gradation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StandardNotFoundException extends RuntimeException {
	public StandardNotFoundException(String message) {
		super(message);
	}
}
