package com.fireduptech.springsecurity.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fireduptech.springsecurity.AthleteAccountDetails;

/*
 * NOTE: Here in the Constructor we hardcode a few AthleteAccountDetails
         domain objects so as to simulate accounts being held in 
         persistent storage.
 */

@Repository
public class AthleteAccountDaoImpl implements AthleteAccountDao {

	private int counter;
	private List<AthleteAccountDetails> adList;

	public AthleteAccountDaoImpl() {
		adList = new ArrayList<AthleteAccountDetails>();
		adList.add( counter++, "athlete1", "free", "John Doe", "JohnDoe@domain.com", "Cycling" );
		adList.add( counter++, "athlete2", "free", "Jane Doe", "JaneDoe@domain.com", "Running" );
	}

	@Override
	AthleteAccountDetails getAthleteAccount( int athleteAccountId ) {

		AthleteAccountDetails locatedAthleteAccount = null;

		for ( AthleteAccountDetails aad : adList ) {
			if ( aad.getId() == athleteAccountId ) {
				locatedAthleteAccount = aad;
				break;
			}
		}
		return locatedAthleteAccount;
	}


	@Override
	List<AthleteAccountDetails> getAllAthleteAccounts() {

		List<AthleteAccountDetails> aad = new ArrayList<AthleteAccountDetails>();

		for ( AthleteAccountDetails details : adList ) {
			aad.add(details);
		}
		return aad;
	}


	@Override
	void saveAthleteAccount(AthleteAccountDetails athleteAccountDetails ) {

		athleteAccountDetails.setId( counter++ );
		adList.add( athleteAccountDetails );
	}


	@Override
	void editAthleteAccount( AthleteAccountDetails modifiedAthleteAccountDetails ) {

		for ( AthleteAccountDetails athleteAccountDetails : adList ) {
			if ( athleteAccountDetails.getId() == modifiedAthleteAccountDetails.getId() ) {
				adList.remove( athleteAccountDetails );
				break;
			}
		}
		adList.add( modifiedAthleteAccountDetails );
	}


	@Override
	void closeAthleteAccount( int athleteAccountId ) {

		for ( AthleteAccountDetails athleteAccountDetails : adList ) {
			if ( athleteAccountDetails.getId() == athleteAccountId ) {
				adList.remove( athleteAccountDetails );
				break;
			}
		}
	}

}
