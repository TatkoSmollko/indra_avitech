package org.assignment.exception;

public class UserPersistenceException extends RuntimeException {
	public UserPersistenceException(String message, Throwable cause) {
		super(message, cause);
	}
}