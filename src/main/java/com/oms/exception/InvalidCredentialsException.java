package com.oms.exception;

public class InvalidCredentialsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9127060378968438234L;
	
	public InvalidCredentialsException(String message) {
		super(message);
	}
	
	public InvalidCredentialsException (String message, Throwable cause) {
		super(message, cause);
	}

}
