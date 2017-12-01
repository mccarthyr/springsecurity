package com.fireduptech.springsecurity.dto;

import javax.validation.constraints.*;

//import org.hibernate.validator.constraints.NotBlank;
//import org.hibernate.validator.constraints.Email;

	
/**
 * UserDto - This is a temporary data transfer object to capture the registration values
 * and transfer to the persistance layer.
 */
public class UserDto {


	//@Email
	private String email;


	private String password;

	//@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;


	private String accountType;


	private String primaryActivity;



	public UserDto() {}


	public UserDto( String email, String password, String firstName, String lastName, String accountType, String primaryActivity ) {

		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountType = accountType;
		this.primaryActivity = primaryActivity;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword( String password ) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType( String accountType ) {
		this.accountType = accountType;
	}


	public String getPrimaryActivity() {
		return primaryActivity;
	}

	public void setPrimaryActivity( String primaryActivity ){
		this.primaryActivity = primaryActivity;
	}


}	// End of class UserDto...




