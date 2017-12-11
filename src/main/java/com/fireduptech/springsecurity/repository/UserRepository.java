package com.fireduptech.springsecurity.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.fireduptech.springsecurity.domain.User;



public interface UserRepository extends Repository<User, String> {


	User save( User entity );


	/*
	 * The Username field (created by the Spring Security ACL tables)
	 * contains the email address as the primary identifier while retaining
	 * the original Spring Security ACL table property name for simplicity.
	 */
	User findByUsername( String email );


}
