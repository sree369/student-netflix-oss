package org.sree.studentnetflixoss.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ParentNotFoundException extends RuntimeException {
	public ParentNotFoundException(String message) {
		super(message);
	}
}
