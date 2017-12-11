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
import com.fireduptech.springsecurity.domain.AthleteAccount;



@Service
public class AthleteAccountServiceImpl implements AthleteAccountService {

	@Autowired
	private AthleteAccountDao athleteAccountDao;

	@Autowired
	private MutableAclService mutableAclService;


	private void addPermission( int athleteAccountId, Sid recipient, Permission permission ) {

		MutableAcl acl;
		ObjectIdentity oid = new ObjectIdentityImpl( AthleteAccount.class, athleteAccountId );

		try {

			acl = (MutableAcl) mutableAclService.readAclById( oid );

		} catch (NotFoundException nfe) {

			acl = mutableAclService.createAcl( oid );
		}

		acl.insertAce( acl.getEntries().size(), permission, recipient, true );
		mutableAclService.updateAcl( acl );

		System.out.println( "Added permission " + permission + " Sid " 
						+ recipient + " athleteAccountId " + athleteAccountId );
	}



	@Override
	public AthleteAccount getAthleteAccount( int athleteAccountId  ) {
		return athleteAccountDao.getAthleteAccount( athleteAccountId );
	}


	@Override
	public List<AthleteAccount> getAllAthleteAccounts() {
		return athleteAccountDao.getAllAthleteAccounts();
	}



	@Override
	public void saveAthleteAccount(AthleteAccount athleteAccount ) {
		
		athleteAccountDao.saveAthleteAccount( athleteAccount );

		addPermission( athleteAccount.getId(), 
						new PrincipalSid( SecurityContextHolder.getContext().getAuthentication().getName() ), 
						BasePermission.READ );
		addPermission( athleteAccount.getId(), 
						new PrincipalSid( SecurityContextHolder.getContext().getAuthentication().getName() ), 
						BasePermission.WRITE );						
	}


	@Override
	public void editAthleteAccount( AthleteAccount athleteAccount ) {
		athleteAccountDao.editAthleteAccount( athleteAccount );
	}


	@Override
	public void closeAthleteAccount( int athleteAccountId ) {

		athleteAccountDao.closeAthleteAccount( athleteAccountId );

		ObjectIdentity oid = new ObjectIdentityImpl( AthleteAccount.class, athleteAccountId );
		mutableAclService.deleteAcl( oid, false );
	}


	@Override
	public void provideAccessToAdmin( int athleteAccountId ) {

		addPermission( athleteAccountId, new PrincipalSid( "admin" ), BasePermission.READ );
		addPermission( athleteAccountId, new PrincipalSid( "admin" ), BasePermission.ADMINISTRATION );
		addPermission( athleteAccountId, new PrincipalSid( "admin" ), BasePermission.DELETE );

	}


}

