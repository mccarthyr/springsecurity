package com.fireduptech.springsecurity.service;


import com.fireduptech.springsecurity.dto.UserDto;
import com.fireduptech.springsecurity.domain.User;

import com.fireduptech.springsecurity.validation.EmailExistsException;


import org.springframework.transaction.annotation.Transactional;


public abstract interface UserService {


	@Transactional
	public abstract User registerNewUserAccount( UserDto userDto ) throws EmailExistsException;


}