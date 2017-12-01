package com.fireduptech.springsecurity.service;

import java.util.List;

import com.fireduptech.springsecurity.domain.AthleteAccount;

import org.springframework.security.access.annotation.Secured;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;


public interface AthleteAccountService {

	//@Secured( "ROLE_ATHLETE" )
	@PreAuthorize( " hasPermission( #athleteAccountId, 'com.fireduptech.springsecurity.domain.AthleteAccount', read ) or " 
								+ " hasPermission( #athleteAccountId, 'com.fireduptech.springsecurity.domain.AthleteAccount', admin ) " )
	AthleteAccount getAthleteAccount( int athleteAccountId  );

	//@Secured( "ROLE_ADMIN" )
	@PreAuthorize( " hasAnyRole( 'ROLE_ADMIN', 'ROLE_ATHLETE' ) " )
	@PostFilter( " hasPermission( filterObject, read ) or hasPermission( filterObject, admin ) " )
	List<AthleteAccount> getAllAthleteAccounts();

	//@Secured( "ROLE_ATHLETE" )
	@PreAuthorize( " hasRole( 'ROLE_ATHLETE' ) " )
	void saveAthleteAccount(AthleteAccount athleteAccount );

	//@Secured( "ROLE_ATHLETE" )
	@PreAuthorize( " hasPermission( #athleteAccount, write ) " )
	void editAthleteAccount( AthleteAccount athleteAccount );

	//@Secured( "ROLE_ADMIN" )
	@PreAuthorize( " hasPermission( #athleteAccountId, 'com.fireduptech.springsecurity.domain.AthleteAccount', delete ) " )
	void closeAthleteAccount( int athleteAccountId );


	@PreAuthorize( " hasPermission( #athleteAccountId, 'com.fireduptech.springsecurity.domain.AthleteAccount', write ) " )
	void provideAccessToAdmin( int athleteAccountId );



	/*
	  ****** FURTHER SAMPLE OPTIONS TO BUILD IN LATER ******
		@PreAuthorize("hasPermission(#fixedDepositId, 'sample.spring.chapter16.domain.FixedDepositDetails', write)")
		void provideAccessToAdmin(int fixedDepositId);

		@PreAuthorize("hasRole('ROLE_CUSTOMER')")
		List<FixedDepositDetails> getFixedDeposits(String user);
	*/


}

