package com.fireduptech.springsecurity.service;


import com.fireduptech.springsecurity.domain.AthleteAccount;
import com.fireduptech.springsecurity.dto.UserDto;

import com.fireduptech.springsecurity.repository.AthleteAccountRepository;

import com.fireduptech.springsecurity.validation.EmailExistsException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import com.fireduptech.springsecurity.domain.User;
import com.fireduptech.springsecurity.domain.Authorities;
import com.fireduptech.springsecurity.domain.AuthorityId;

import com.fireduptech.springsecurity.repository.UserRepository;
import com.fireduptech.springsecurity.repository.AuthoritiesRepository;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.transaction.annotation.Transactional;



@Service
public class UserServiceImpl implements UserService {

	final String AUTHORITY_DEFAULT_REGISTERED_USER = "ROLE_ATHLETE";

	@Autowired
	protected AthleteAccountRepository athleteAccountRepository;

	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected AuthoritiesRepository authoritiesRepository;

	@Autowired
	protected PasswordEncoder passwordEncoder;



	/*
	 * @param - UserDTO object - contains information from User Registration form.
	 *
	 * @Transactional - This method is Transactionsal as defined in its interface.
	 */
	@Override
	public User registerNewUserAccount( final UserDto userDto ) throws EmailExistsException {

		String email = userDto.getEmail();
		String password = userDto.getPassword();

		if ( emailExists( email ) ) {
			throw new EmailExistsException( "There is an account already registered with that email address:" );
		}

		/*  This is no longer needed as creating a new AthleteAccount it done through the AthleteController 
				once the new User is registered and logged in.

		AthleteAccount newAthleteAccount = new AthleteAccount();
		newAthleteAccount.setEmail( email );
		newAthleteAccount.setFirstName( userDto.getFirstName() );
		newAthleteAccount.setLastName( userDto.getLastName() );
		newAthleteAccount.setAccountType( userDto.getAccountType() );
		newAthleteAccount.setPrimaryActivity( userDto.getPrimaryActivity() );
		AthleteAccount registeredAthlete = athleteAccountRepository.save( newAthleteAccount );
		*/

		User registeredAclUser = null;	

		registeredAclUser =  updateAclTablesWithNewRegisteredAthlete( email, password );
		return registeredAclUser;

	}



	/*
	 * @param - String - email
	 * @param - String - password
	 *
	 * Creates entries in the users and authorities ACL tables for the newly registered user.
	 * If there is an issue with this then a transactional rollback will happen as this method is
	 * under the @Transactional scope.
	*/
	private User updateAclTablesWithNewRegisteredAthlete( final String email, final String password )  {


		// Save to the Users ACL
		Integer i = new Integer(1);
		short enabled = i.shortValue();

		String encryptedPassword = passwordEncoder.encode( password );

		User user = new User( email, encryptedPassword, enabled );

		User registeredAclUser = userRepository.save( user );


		/* 
			NOTE: To check that the @Transactional is working, uncomment the 'throw new ...' exception line below while then also
						commenting out the rest of the method. This will
		 				will throw the required RuntimeException that will cause a Transactional Rollback and the values that would have been entered
						in the athlete_account and users tables will be removed so they do not fall out of sync.						
						throw new RuntimeException( "FORCED RUNTIME EXCEPTION" );
		*/



		// Save to the Authorities ACL - 'ROLE_ATHLETE' is the hardcoded default Role for demo code 
		AuthorityId authorityId = new AuthorityId( user, AUTHORITY_DEFAULT_REGISTERED_USER );

		Authorities authorities = new Authorities();
		authorities.setId( authorityId );

		Authorities registeredAuthority = authoritiesRepository.save( authorities );
		return registeredAclUser;

	}

	

	/*
	 * In this demo project the domain User 'username' field contains the email as
	 * the email is being used as the primary Id. The 'username' field has been left
	 * as it was originally called in the ACL users table when setup.
	 */
	private boolean emailExists( String email ) {

		User user = userRepository.findByUsername( email );
		if ( user != null ) {
			return true;
		} else {
			return false;
		}

	}

}



