package com.fireduptech.springsecurity.service;


import com.fireduptech.springsecurity.domain.User;

import com.fireduptech.springsecurity.validation.EmailExistsException;

public abstract interface UserService {


//	public abstract User registerNewUserAccount( UserDto accountDto ); //throws EmailExistsException;

public abstract User registerNewUserAccount( User accountDto ); //throws EmailExistsException;


}