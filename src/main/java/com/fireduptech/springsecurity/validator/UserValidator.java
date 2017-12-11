package com.fireduptech.springsecurity.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;


import com.fireduptech.springsecurity.dto.UserDto;

public class UserValidator implements Validator {

	@Override
	public boolean supports( Class<?> clazz ) {
		return UserDto.class.isAssignableFrom( clazz );
	}


	@Override
	public void validate( Object target, Errors errors ) {

		UserDto userDto = ( UserDto ) target;

		// Checking that the passwords are matching
		String password = userDto.getPassword();
		String confirmPassword = userDto.getConfirmPassword();
		if ( !password.equals( confirmPassword ) ) {
			errors.rejectValue( "confirmPassword", "error.password.notMatching", "passwords not matching" );
		}
		

		/*
		String name = userDto.getFirstName();
		if ( !name.equals( "Richard" ) ) {
			//errors.reject( "Incorrect First Name" );
			errors.rejectValue( "firstName", "error.firstName.incorrect", "Incorrect First Name" );
		}
		*/

		/*
		String email = userDto.getEmail();
		if (email == null || "".equalsIgnoreCase(email)) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
					"error.email.blank", "must not be blank123");
		} else if (!email.contains("@")) {
			errors.rejectValue("email", "error.email.invalid",
					"not a well-formed email address");
		}
		*/

	}


}