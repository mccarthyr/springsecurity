package com.fireduptech.springsecurity.domain;


import javax.validation.constraints.*;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Email;


// *** NOTE *** THIS CLASS WILL EVENTUALL CONNECT TO JPA AND BE MAPPED TO THE USERS TABLE IN THE DATABASE...
//							SOME OF THE FIELDS FROM THE ATHLETE ACCOUNT DETAILS CLASS WILL BE ABLE TO GO IN HERE ...


public class User {


	// THE USERS TABLE DOESN'T HAVE AN ID ON IT BUT WILL PUT ONE ON IT WHEN USE JPA AND CHECK IT DOESN'T BREAK THE ACL SYSTEM...
	private long userId;


	private String username;

	private String password;

	//@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	//@Email
	private String email;


	public User() {}

	public User( long userId, String username, String password, 
							String firstName, String lastName, String email ) {

		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}


	// Getters

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getEmail() {
		return this.email;
	}

	// Setters

	public void setUsername( String username ) {
		this.username = username;
	}

	public void setPassword( String password ) {
		this.password = password;
	}

	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}

	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	public void setEmail( String email ) {
		this.email = email;
	}


}	// End of class User...