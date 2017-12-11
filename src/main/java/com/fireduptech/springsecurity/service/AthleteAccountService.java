package com.fireduptech.springsecurity.service;

import java.util.List;

import com.fireduptech.springsecurity.domain.AthleteAccount;

import org.springframework.security.access.annotation.Secured;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.transaction.annotation.Transactional;


public interface AthleteAccountService {

	//@Secured( "ROLE_ATHLETE" )
	@PreAuthorize( " hasPermission( #athleteAccountId, 'com.fireduptech.springsecurity.domain.AthleteAccount', read ) or " 
								+ " hasPermission( #athleteAccountId, 'com.fireduptech.springsecurity.domain.AthleteAccount', admin ) " )
	AthleteAccount getAthleteAccount( int athleteAccountId  );


	//@Secured( "ROLE_ADMIN" )
	@PreAuthorize( " hasAnyRole( 'ROLE_ADMIN', 'ROLE_ATHLETE' ) " )
	@PostFilter( " hasPermission( filterObject, read ) or hasPermission( filterObject, admin ) " )
	List<AthleteAccount> getAllAthleteAccounts();


	@PreAuthorize( " hasRole( 'ROLE_ATHLETE' ) " )
	@Transactional
	void saveAthleteAccount(AthleteAccount athleteAccount );


	@PreAuthorize( " hasPermission( #athleteAccount, write ) " )
	void editAthleteAccount( AthleteAccount athleteAccount );


	@PreAuthorize( " hasPermission( #athleteAccountId, 'com.fireduptech.springsecurity.domain.AthleteAccount', delete ) " )
	void closeAthleteAccount( int athleteAccountId );


	@PreAuthorize( " hasPermission( #athleteAccountId, 'com.fireduptech.springsecurity.domain.AthleteAccount', write ) " )
	void provideAccessToAdmin( int athleteAccountId );


}

