package com.fireduptech.springsecurity.service;


import com.fireduptech.springsecurity.dto.UserDto;
import com.fireduptech.springsecurity.domain.AthleteAccount;

import com.fireduptech.springsecurity.validation.EmailExistsException;

public abstract interface UserService {


//	public abstract User registerNewUserAccount( UserDto accountDto ); //throws EmailExistsException;

public abstract AthleteAccount registerNewUserAccount( UserDto userDto ); //throws EmailExistsException;


}