package com.fireduptech.springsecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fireduptech.springsecurity.dao.AthleteAccountDao;
import com.fireduptech.springsecurity.domain.AthleteAccountDetails;


@Service
public class AthleteAccountServiceImpl implements AthleteAccountService {

	@Autowired
	private AthleteAccountDao athleteAccountDao;

	@Autowired
	private MutableAclService mutableAclService;



	private void addPermission( long athleteAccountId, Sid recipient, Permission permission ) {

		MutableAcl acl;
		ObjectIdentity oid = new ObjectIdentityImpl( AthleteAccountDetails.class, athleteAccountId );

		try {
			acl = (MutableAcl) mutableAclService.readAclById( oid );
		} catch ( NotFoundException nfe ) {
			acl = mutableAclService.createAcl( oid );
		}

		acl.insertAce( acl.getEntries().size(), permission, recipient, true );
		mutableAclService.updateAcl( acl );

		System.out.println( "Added permission " + permission + " Sid " 
				+ recipient + " athleteAccountId " + athleteAccountId );
	}


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

		addPermission( athleteAccountDetails.getId(), 
						new PrincipalSid( SecurityContextHolder.getContext().getAuthentication().getName() ), 
						BasePermission.READ );
		addPermission( athleteAccountDetails.getId(), 
						new PrincipalSid( SecurityContextHolder.getContext().getAuthentication().getName() ), 
						BasePermission.WRITE );
	}


	@Override
	public void editAthleteAccount( AthleteAccountDetails athleteAccountDetails ) {
		athleteAccountDao.editAthleteAccount( athleteAccountDetails );
	}


	@Override
	public void closeAthleteAccount( int athleteAccountId ) {

		athleteAccountDao.closeAthleteAccount( athleteAccountId );

		ObjectIdentity oid = new ObjectIdentityImpl( AthleteAccountDetails.class, athleteAccountId );
		mutableAclService.deleteAcl( oid, false );
	}


	@Override
	public void provideAccessToAdmin( int athleteAccountId ) {

		addPermission( athleteAccountId, new PrincipalSid( "admin" ), BasePermission.READ );
		addPermission( athleteAccountId, new PrincipalSid( "admin" ), BasePermission.ADMINISTRATION );
		addPermission( athleteAccountId, new PrincipalSid( "admin" ), BasePermission.DELETE );

	}


	/*
	 ****** FURTHER SAMPLE OPTION TO BUILD IN LATER ******
	 	@Override
		public void provideAccessToAdmin(int fixedDepositId) {
			addPermission(fixedDepositId, new PrincipalSid("admin"),
					BasePermission.READ);
			addPermission(fixedDepositId, new PrincipalSid("admin"),
					BasePermission.ADMINISTRATION);
			addPermission(fixedDepositId, new PrincipalSid("admin"),
					BasePermission.DELETE);
		}
	*/

}


