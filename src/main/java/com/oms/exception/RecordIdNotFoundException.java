package com.oms.exception;

public class RecordIdNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7204072759371513548L;
	
	public RecordIdNotFoundException(String message) {
		super(message);
	}
	
	public RecordIdNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
