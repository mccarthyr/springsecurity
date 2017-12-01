package com.fireduptech.springsecurity.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.validation.BindingResult;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.fireduptech.springsecurity.domain.AthleteAccount;
import com.fireduptech.springsecurity.dto.UserDto;
import com.fireduptech.springsecurity.service.UserService;
import com.fireduptech.springsecurity.validation.EmailExistsException;

import com.fireduptech.springsecurity.validator.UserValidator;



// Security Imports
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


@Controller
public class RegistrationController {


	@Autowired
	private UserService userService;



	// Uncomment these to explicitly use Spring Validation API
	//@Autowired
	//private Validator validator;
	

	/*
	 * When @RequestMapping and @ModelAttribute are used together, the View is taken from the
	 * URI in the requestMapping (the path field below) and the returned object is set as a model attribute.
	 * Springs uses the RequestToViewNameTranslator class to determine the view based on the request URI
	*/

	@RequestMapping( path = "/registration", method = RequestMethod.GET )
	@ModelAttribute( name = "user" )
	public UserDto showRegistrationPage() {

		return new UserDto();

	}	// End of method showRegistrationPage()...


/*
*** SET UP METHOD BELOW LIKE openFixedDeposit
   SO WILL RETURN A STRING WHICH WILL BE EITHER AFTER SUCCESS A REDIRECT TO THE METHOD THAT WILL DISPLAY THE SUCCESS VIEW DIRECTLY (return "redirect:/fixedDeposit/list";)
   OR THERE ARE VALIDATION ERRORS (SET UP FAKE EMAIL FAIL) AND RETURN A STRING THAT IS THE NAME OF THE FORM VIEW WE JUST CAME FROM (registration)...

   showOpenFixedDepositForm()
   openFixedDeposit()
   listFixedDeposits() (success outcome)
******/

	
	@InitBinder( value = "user" )
	public void initBinder_New( WebDataBinder webDataBinder ) {
		//webDataBinder.setValidator( new UserValidator() );
	}	// End of method initBinder_New



	@RequestMapping( path = "register/user", params = "acAction=register", method = RequestMethod.POST )	
	//public ModelAndView registerNewUserAccount( @ModelAttribute( "user" ) @Valid final User user, final BindingResult bindingResult, RedirectAttributes redirectAttributes ) {
	//public ModelAndView registerNewUserAccount( @ModelAttribute( "user" ) @Valid final User user, final BindingResult bindingResult, ModelMap model ) {
	public String registerNewUserAccount( @ModelAttribute( "user" ) @Valid final UserDto user, final BindingResult bindingResult, RedirectAttributes redirectAttributes ) {

		new UserValidator().validate( user, bindingResult ); // Use with @Valid in method argument to execute both annotations an custom class
		// NOTE: If not using @Valid annotation (with @InitBinder method) then use above line to execute my UserValidator class

		// This uses Spring Validation API and will validate the annotations in the domain User object automatically, remove @Valid when using this.
		//validator.validate( user, bindingResult );  


		AthleteAccount registered = null;

		if ( bindingResult.hasErrors() ) {

			return "registration";
			//return new ModelAndView( "registration" );

		} else {

			// NOTE: CURRENTLY RETURNING SIMULATED SUCCESSFUL REGISTER
			registered = userService.registerNewUserAccount( user );





			/*
			--------------
			NEXT STEPS:
			--------------

			Complete the User Registration Process. This involves:
			- Setting up Full Database Model for this
			- JPA Database setup
			- Password Encoding
			*/
			/*
				(DONE) 1  - NEXT STEP IS TO COMPILE AS IS TO SEE EVERYTHING DOWNLOADS OK AND CONFIG IS OK (UNLESS RUN TIME ISSUE LATER)
				(DONE) 2  - DEPLOY IT THEN JUST TO SEE ALL IS STILL RUNNING OK.
				(DONE) 3  - THEN MODEL DATABASE ON PAPER AND WHAT WANT FROM IT
				(DONE) 4  - THEN SET UP THE DOMAIN DTO USER OBJECT WITH PERSISTENCE ANNOTATIONS 
				(DONE) 5  - THEN SET UP THE DOMAIN ATHLETE ACCOUNT DETAILS OBJECT FOR PERSISTENCE 
				(DONE) 6  - GET AN ENTRY SAVED INTO THE ATHLETE ACCOUNT DETAILS TABLE
				7  - THEN GET CORRESPONDING ENTRY UPDATED INTO THE USERS & AUTHORITIES TABLE (ACL SYSTEM)
				8  - TEST THAT IS USER IS BEEN REGISTERED TO THE DATABASE AND SEE HOW THIS IS WORKING WITH SPRING SECURITY ACL
				9  - ONCE THAT IS ALL DONE THEN INTEGRATE IT WITH PROGAMMATIC LOGIN.
				10 - SET UP AND IMPLEMENT THE PASSWORD ENCODING SERVICE AND RETRO-FIT REGISTRATION SYSTEM WITH IT
				11 - FINAL PART ONCE ALL IS WORKING IS TO PUT THE REGISTRATION WITHIN A TRANSACTION - CODE FOR DEMO CODE AND TO WRITE SMALL ARTICLE ABOUT
			*/


			// NOTE NOTE NOTE - RETURNING THIS (FORCES LOGIN) UNTIL FULL REGISTRATION SYSTEM IS IN PLACE...
			return "redirect:/athleteaccountv2/athleteAccount/list";

			/*
			List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList( "ROLE_ATHLETE" );
			//Authentication auth = new UsernamePasswordAuthenticationToken( user, user.getPassword(), authorities );
			Authentication auth = new UsernamePasswordAuthenticationToken( registered, registered.getPassword(), authorities );
			SecurityContextHolder.getContext().setAuthentication( auth );

			// Flash attributes are saved in the HTTP session and hence do not appear in the URL
			redirectAttributes.addFlashAttribute( "successfulRegistrationFlashMessage", "You have been successfully registered" );
			return "redirect:/athleteaccountv2/athleteAccount/list";
			*/

			
		}


/*
		*** NOTE ---> KEEP THESE NOTES BELOW FOR COMMENT DOCUMENTATION ***
		WHAT I WANT FOR THIS IS AN ACTUUAL "FORWARD" REQUEST AS IT ALL STAYS SERVER SIDE.
		A REDIRECT TECHNICALLY RETURNS TO THE CLIENT BROWSER WITH A 302 AND ISSUES ANOTHER REQUEST - SO THE URLS CAN BE MONITORED...
		Like with the Redirect, the forward: prefix will be resolved by the UrlBasedViewResolver and its subclasses. Interally this will create 
		an InternalResourceView which does a RequestDispatcher.forward() to the new view...

		UNLIKE 'redirect' THE ATTRIBUTES IN MODEL DO NOT PROPAGATE AUTOMATICALLY IN CASE OF FORWARDING, so NEEDS A MANUAL APPROACH USING HTTPSERVLETREQUEST REQUEST VARIABLE...

		WITH A FORWARD (SERVER SIDE) THE ORIGINAL REQUEST STAYS INTACT - So a POST remains a POST which will NOT suit forward to Login controller as that is a GET. WILL REQUIRE A PROGRAMMATIC LOGIN INTERNALLY

		IF THE USER IS SUCCESSFULL IN THE REGISTRATION, RATHER THEN SENDING THEM BACK TO A DEAD END SUCCESSFUL REGISTRATION PAGE, THIS IS WHERE A 
		STRAIGHT FORWARDING COULD COME INTO PLAY AND FORWARD THEM TO LOGIN...
*/ 

	}	// End of method registerNewUserAccount()...


	/*
	@RequestMapping( path = "/register/user", params = "acAction=register", method = RequestMethod.POST )
	public ModelAndView registerNewUserAccount( @ModelAttribute( "user" ) @Valid final UserDto accountDto, final BindingResult result, ModelMap model ) {
		
		User registered = null;

		if ( result.hasErrors() ) {
			return new ModelAndView( "registration", "user", accountDto );
		}		

		try {
			registered = userService.registerNewUserAccount( accountDto );
		} catch ( EmailExistsException eee ) {
			model.addAttribute( "error.emailAlreadyExists", "The supplied email already exists on the system" );
			ModelMap m = new ModelMap( "user", registered );
			return new ModelAndView( "successfulRegister", m );
		}

		if ( registered == null ) {
			return new ModelAndView( "registration", "user", accountDto );
		}

		return new ModelAndView( "successfulRegister", "user", registered );

	}	// End of method registerNewUserAccount()...
	*/



}	// End of class RegistrationController...



