package com.fireduptech.springsecurity.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.fireduptech.springsecurity.domain.Authorities;
import com.fireduptech.springsecurity.domain.AuthorityId;


public interface AuthoritiesRepository extends Repository<Authorities, AuthorityId> {


	Authorities save( Authorities entity );


}
