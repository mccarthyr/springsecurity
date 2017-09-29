package com.fireduptech.springsecurity.service;

import java.util.List;

import com.fireduptech.springsecurity.domain.AthleteAccountDetails;

import org.springframework.security.access.annotation.Secured;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;


public interface AthleteAccountService {

	//@Secured( "ROLE_ATHLETE" )
	@PreAuthorize( " hasPermission( #athleteAccountId, 'com.fireduptech.springsecurity.domain.AthleteAccountDetails', read ) or " 
								+ " hasPermission( #athleteAccountId, 'com.fireduptech.springsecurity.domain.AthleteAccountDetails', admin ) " )
	AthleteAccountDetails getAthleteAccount( int athleteAccountId  );

	//@Secured( "ROLE_ADMIN" )
	@PreAuthorize( " hasPermission( 'ROLE_ADMIN' ) " )
	@PostFilter( " hasPermission( filterObject, read ) or hasPermission( filterObject, admin ) " )
	List<AthleteAccountDetails> getAllAthleteAccounts();

	//@Secured( "ROLE_ATHLETE" )
	@PreAuthorize( " hasRole( 'ROLE_ATHLETE' ) " )
	void saveAthleteAccount(AthleteAccountDetails athleteAccountDetails );

	//@Secured( "ROLE_ATHLETE" )
	@PreAuthorize( " hasPermission( #athleteAccountDetails, write ) " )
	void editAthleteAccount( AthleteAccountDetails athleteAccountDetails );

	//@Secured( "ROLE_ADMIN" )
	@PreAuthorize( " hasPermission( #athleteAccountId, 'com.fireduptech.springsecurity.domain.AthleteAccountDetails', delete ) " )
	void closeAthleteAccount( int athleteAccountId );


	/*
	  ****** FURTHER SAMPLE OPTIONS TO BUILD IN LATER ******
		@PreAuthorize("hasPermission(#fixedDepositId, 'sample.spring.chapter16.domain.FixedDepositDetails', write)")
		void provideAccessToAdmin(int fixedDepositId);

		@PreAuthorize("hasRole('ROLE_CUSTOMER')")
		List<FixedDepositDetails> getFixedDeposits(String user);
	*/


}

