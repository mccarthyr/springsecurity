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



/*
	*** NEED IN UPGRADE VERSION OF THIS TO DEAL WITH THROW EXCEPTIONS WHEN UPDATING THE ACL TABLES SO CAN STOP IF SOMETHING GOES WRONG AT THIS POINT OR 
				ELSE LET THE TRANSACTION STUFF DEAL WITH IT ???
*/


@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private AthleteAccountRepository athleteAccountRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthoritiesRepository authoritiesRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

 

	public User registerNewUserAccount( final UserDto userDto ) {

		/*
		if ( emailExists( accountDto.getEmail() ) ) {
			throw new EmailExistsException( "There is an account already registered with that email address: " + accountDto.getEmail() );
		}*/


		AthleteAccount newAthleteAccount = new AthleteAccount();
		
		String email = userDto.getEmail();
		newAthleteAccount.setEmail( email );

		String password = userDto.getPassword();

		newAthleteAccount.setFirstName( userDto.getFirstName() );
		newAthleteAccount.setLastName( userDto.getLastName() );
		newAthleteAccount.setAccountType( userDto.getAccountType() );
		newAthleteAccount.setPrimaryActivity( userDto.getPrimaryActivity() );		


		// @TODO - SETTING OF THE PASSWORD IS DONE ON THE USERS TABLE - THIS WILL BE DEALTH WITH SEPARATELY WITH A DIFFERENT SERVICE METHOD AND WITHIN A TRANSACTION BLOCK <---


		AthleteAccount registeredAthlete = athleteAccountRepository.save( newAthleteAccount );

		User registeredAclUser =  updateAclTablesWithNewRegisteredAthlete( email, password );
		

		//BEST TO RETURN THE USER ENTITY HERE AS IT WILL CONTAIN THE USERNAME AND PASSWORD (WHICH EVENTUALLY WILL BE ENCODED) AND USE THAT TO LOG IN...

		return registeredAclUser;

		//return registeredAthlete;

	}	// End of method registerNewUserAccount()...


	/*
	Users 			- username, password, enabled 
	Authorities - username, authority

	BY DEFAULT EACH NEW REGISTERED USER GETS A ROLE OF ROLE_ATHLETE - this is hardcoded into this demo version...could look at a more sophisticated registration system if was to make this commercial...
	*/
	private User updateAclTablesWithNewRegisteredAthlete( final String email, final String password ) {

		// Save to the Users ACL
		Integer i = new Integer(1);
		short enabled = i.shortValue();

		String encryptedPassword = passwordEncoder.encode( password );

		User user = new User( email, encryptedPassword, enabled );



		User registeredAclUser = userRepository.save( user );

		// Save to the Authorities ACL
		AuthorityId authorityId = new AuthorityId( user, "ROLE_ATHLETE" );

		Authorities authorities = new Authorities();
		authorities.setId( authorityId );

		Authorities registeredAuthority = authoritiesRepository.save( authorities );

		return registeredAclUser;


	}	// End of method updateAclTablesWithNewRegisteredAthlete()...

	

	private boolean emailExists( String email ) {
		//return true;
		return false;
	}





}	// End of class UserService...




