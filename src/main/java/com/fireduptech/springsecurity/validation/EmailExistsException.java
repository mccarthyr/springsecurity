package com.fireduptech.springsecurity.validation;


public class EmailExistsException extends Exception {


	public EmailExistsException( final String message ) {
		super( message );
	}


}