package com.fireduptech.springsecurity.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.fireduptech.springsecurity.domain.AthleteAccountDetails;


public interface AthleteAccountService {

	@Secured( "ROLE_ATHLETE" )
	AthleteAccountDetails getAthleteAccount( int athleteAccountId  );

	@Secured( "ROLE_ADMIN" )
	List<AthleteAccountDetails> getAllAthleteAccounts();

	@Secured( "ROLE_ATHLETE" )
	void saveAthleteAccount(AthleteAccountDetails athleteAccountDetails );

	@Secured( "ROLE_ATHLETE" )
	void editAthleteAccount( AthleteAccountDetails athleteAccountDetails );

	@Secured( "ROLE_ADMIN" )
	void closeAthleteAccount( int athleteAccountId );

}
