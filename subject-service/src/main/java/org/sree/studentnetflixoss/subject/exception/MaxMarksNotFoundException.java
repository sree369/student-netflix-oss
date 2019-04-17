package org.sree.studentnetflixoss.subject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MaxMarksNotFoundException extends RuntimeException {
	public MaxMarksNotFoundException(String message) {
		super(message);
	}
}
