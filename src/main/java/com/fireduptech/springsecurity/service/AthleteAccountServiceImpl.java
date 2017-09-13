package com.fireduptech.springsecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fireduptech.springsecurity.dao.AthleteAccountDao;
import com.fireduptech.springsecurity.domain.AthleteAccountDetails;


@Service
public class AthleteAccountServiceImpl implements AthleteAccountService {

	@Autowired
	private AthleteAccountDao athleteAccountDao;

	@Override
	public AthleteAccountDetails getAthleteAccount( int athleteAccountId  ) {
		return athleteAccountDao.getAthleteAccount( athleteAccountId );
	}

	@Override
	public List<AthleteAccountDetails> getAllAthleteAccounts() {
		return athleteAccountDao.getAllAthleteAccounts();
	}

	@Override
	public void saveAthleteAccount(AthleteAccountDetails athleteAccountDetails ) {
		athleteAccountDao.saveAthleteAccount( athleteAccountDetails );
	}

	@Override
	public void editAthleteAccount( AthleteAccountDetails athleteAccountDetails ) {
		athleteAccountDao.editAthleteAccount( athleteAccountDetails );
	}

	@Override
	public void closeAthleteAccount( int athleteAccountId ) {
		athleteAccountDao.closeAthleteAccount( athleteAccountId );
	}

}

