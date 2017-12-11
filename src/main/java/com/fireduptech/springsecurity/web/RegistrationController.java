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

import com.fireduptech.springsecurity.domain.User;
import com.fireduptech.springsecurity.domain.AthleteAccount;

import com.fireduptech.springsecurity.dto.UserDto;

import com.fireduptech.springsecurity.service.UserService;
import com.fireduptech.springsecurity.service.UserServiceImpl;

import com.fireduptech.springsecurity.validation.EmailExistsException;
import com.fireduptech.springsecurity.validator.UserValidator;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.transaction.annotation.Transactional;



@Controller
public class RegistrationController {


  final String REGISTRATION_URI = "registration";
	final String REDIRECT_REGISTRATION_URI = "redirect:/athleteaccountv2/registration";
	final String REDIRECT_ATHLETE_ACCOUNTS_LIST_URI = "redirect:/athleteaccountv2/athleteAccount/list";


	@Autowired
	private UserService userService;


	// Uncomment these to explicitly use Spring Validation API
	/* 
		@Autowired
		private Validator validator;
	*/	


	/*
	 * When @RequestMapping and @ModelAttribute are used together, the View is taken from the
	 * URI in the requestMapping (the path field below) and the returned object is set as a model attribute.
	 * Springs uses the RequestToViewNameTranslator class to determine the view based on the request URI
	*/
	@RequestMapping( path = "/registration", method = RequestMethod.GET )
	@ModelAttribute( name = "user" )
	public UserDto showRegistrationPage() {

		return new UserDto();

	}


	@InitBinder( value = "user" )
	public void initBinder_New( WebDataBinder webDataBinder ) {
		//webDataBinder.setValidator( new UserValidator() );
	}



	@RequestMapping( path = "register/user", params = "acAction=register", method = RequestMethod.POST )	
 	public String registerNewUserAccount( @ModelAttribute( "user" ) @Valid final UserDto user, final BindingResult bindingResult, RedirectAttributes redirectAttributes )  {


		/*
		 * NOTE: Use the below line with @Valid in method argument to execute both annotations and a custom class. 
		 * 			 No 'webDataBinder.setValidator' definition in this case.
		 *
		 *			 If not using @Valid annotation (along with the @InitBinder method) then use line below to execute my UserValidator class
		 */

		new UserValidator().validate( user, bindingResult ); 


		// This uses Spring Validation API and will validate the annotations in the domain User object automatically, remove @Valid when using this.
		//validator.validate( user, bindingResult );  



		User registeredAthlete = null;

		if ( bindingResult.hasErrors() ) {

			return REGISTRATION_URI;

		} else {

			try {

				registeredAthlete = userService.registerNewUserAccount( user );

			} catch ( RuntimeException e ) {

				redirectAttributes.addFlashAttribute( "registrationErrorFlashMessage", "Registration Error: There was an issue with the registration process, technical support has been notified." );
				return REDIRECT_REGISTRATION_URI;

			} catch ( EmailExistsException e ) {

				redirectAttributes.addFlashAttribute( "registrationErrorFlashMessage", "The email address provided already exists in the system." );
				return REDIRECT_REGISTRATION_URI;

			}


			List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList( "ROLE_ATHLETE" );
			Authentication auth = new UsernamePasswordAuthenticationToken( registeredAthlete, registeredAthlete.getPassword(), authorities );
			SecurityContextHolder.getContext().setAuthentication( auth );

			// Flash attributes are saved in the HTTP session and hence do not appear in the URL
			redirectAttributes.addFlashAttribute( "successfulRegistrationFlashMessage", "You have been successfully registered" );
	
			return REDIRECT_ATHLETE_ACCOUNTS_LIST_URI;

		}
	}


}



