package com.oms.exception;

public class NoRecordFoundException extends RuntimeException {

	private static final long serialVersionUID = -9210071596763086671L;
	

	public NoRecordFoundException(String message) {
		super(message);
	}
	
	public NoRecordFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
