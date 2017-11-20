package com.fireduptech.springsecurity.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;


import com.fireduptech.springsecurity.domain.User;

public class UserValidator implements Validator {

	@Override
	public boolean supports( Class<?> clazz ) {
		return User.class.isAssignableFrom( clazz );
	}

	@Override
	public void validate( Object target, Errors errors ) {

		User user = ( User ) target;
		String name = user.getFirstName();
		if ( !name.equals( "Richard" ) ) {
			//errors.reject( "Incorrect First Name" );
			errors.rejectValue( "firstName", "error.firstName.incorrect", "Incorrect First Name" );
		} 

		String email = user.getEmail();

		if (email == null || "".equalsIgnoreCase(email)) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
					"error.email.blank", "must not be blank123");
		} else if (!email.contains("@")) {
			errors.rejectValue("email", "error.email.invalid",
					"not a well-formed email addressXXX");
		}


	}

	/*
	public void validate( Object target, Errors errors ) {

		UserDto userDto = ( UserDto ) target;
		if ( userDto.getFirstName() != "Richard" ) {
			errors.reject( "Incorrect First Name" );
		} 

	}
	*/



}