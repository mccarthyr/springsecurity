package com.fireduptech.springsecurity.dao;

import java.util.List;

import com.fireduptech.springsecurity.domain.AthleteAccount;

public interface AthleteAccountDao {

	AthleteAccount getAthleteAccount( int athleteAccountId );

	List<AthleteAccount> getAllAthleteAccounts();

	void saveAthleteAccount(AthleteAccount athleteAccount );

	void editAthleteAccount( AthleteAccount athleteAccount );

	void closeAthleteAccount( int athleteAccountId );
}

