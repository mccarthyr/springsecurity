package com.fireduptech.springsecurity.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.fireduptech.springsecurity.domain.User;



public interface UserRepository extends Repository<User, String> {


	User save( User entity );


}
