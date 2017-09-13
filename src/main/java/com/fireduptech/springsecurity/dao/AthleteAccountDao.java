pacakge com.fireduptech.springsecurity.dao;

import java.util.List;

import com.fireduptech.springsecurity.domain.AthleteAccountDetails;

public interface AthleteAccountDao{

	AthleteAccountDetails getAthleteAccount( int athleteAccountId );

	List<AthleteAccountDetails> getAllAthleteAccounts();

	void saveAthleteAccount(AthleteAccountDetails athleteAccountDetails );

	void editAthleteAccount( AthleteAccountDetails athleteAccountDetails );

	void closeAthleteAccount( int athleteAccountId );
}

