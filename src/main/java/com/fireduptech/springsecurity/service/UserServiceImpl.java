package com.fireduptech.springsecurity.service;


import com.fireduptech.springsecurity.domain.User;

import com.fireduptech.springsecurity.validation.EmailExistsException;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


	// ****** PUT IN JPA RESPOITORY AUTOWIRED SERVICE HERE 

	// WILL USE JPA FOR CHECKING IF AN EMAIL ALREADY EXISTS ALSO



//	public User registerNewUserAccount( final UserDto accountDto ) { // throws EmailExistsException {
	public User registerNewUserAccount( final User accountDto ) { // throws EmailExistsException {

		/*
		if ( emailExists( accountDto.getEmail() ) ) {
			throw new EmailExistsException( "There is an account already registered with that email address: " + accountDto.getEmail() );
		}*/

		User user = new User();
		//user.setUsername( accountDto.getUsername() );
		user.setUsername( "athlete1" );
		user.setFirstName( accountDto.getFirstName() );
		user.setLastName( accountDto.getLastName() );
		user.setEmail( accountDto.getEmail() );

		// ****** NOTE NOTE NOTE: DEAL WITH PASSWORD ENCODING HERE ******
		user.setPassword( "athlete1" );

		// THE USER ID WILL BE GENERATED IN THE DATABASE THE FIRST TIME OUT DURING REGISTRATION...

		// NOTE DATABASE SAVING FOR NOW...

		return user;

	}	// End of method registerNewUserAccount()...



	private boolean emailExists( String email ) {
		//return true;
		return false;
	}





}	// End of class UserService...




