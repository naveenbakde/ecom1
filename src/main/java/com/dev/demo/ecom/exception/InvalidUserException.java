package com.dev.demo.ecom.exception;

@SuppressWarnings("serial")
public class InvalidUserException extends RuntimeException {
	
	public InvalidUserException() {
		super();
	}
	
	public InvalidUserException(String str) {
		super(str);
	}

}
