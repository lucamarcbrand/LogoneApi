package com.app.service.exception;

//User service related exceptions are handled by this class
public class UserServiceException extends RuntimeException{
	private static final long serialVersionUID = -879313250652130413L;

	public UserServiceException(String exception){
		super(exception);
	}
}
