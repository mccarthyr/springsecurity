package com.fireduptech.springsecurity.dto;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Email;

	
/**
 * UserDto - This is a temporary data transfer object to capture the registration values
 * and transfer to the persistance layer.
 */
public class UserDto {


	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	private String confirmPassword;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotBlank
	private String accountType;

	@NotBlank
	private String primaryActivity;



	public UserDto() {}


	public UserDto( String email, String password, String confirmPassword, String firstName, String lastName, String accountType, String primaryActivity ) {

		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword( String confirmPassword ) {
		this.confirmPassword = confirmPassword;
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

}




