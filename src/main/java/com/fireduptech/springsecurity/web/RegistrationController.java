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
import com.fireduptech.springsecurity.dto.UserDto;
import com.fireduptech.springsecurity.service.UserService;
import com.fireduptech.springsecurity.validation.EmailExistsException;

import com.fireduptech.springsecurity.validator.UserValidator;



@Controller
public class RegistrationController {


	@Autowired
	private UserService userService;


	// Uncomment these to explicitly use Spring Validation API
	//@Autowired
	//private Validator validator;
	

	/*
	 * WHen @RequestMapping and @ModelAttribute are used together, the View is taken from the
	 * URI in the requestMapping (the path field below) and the returned object is set as a model attribute.
	 * Springs uses the RequestToViewNameTranslator class to determine the view based on the request URI
	*/

	@RequestMapping( path = "/registration", method = RequestMethod.GET )
	@ModelAttribute( name = "user" )
	public User showRegistrationPage() {

		return new User();

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
	public String registerNewUserAccount( @ModelAttribute( "user" ) @Valid final User user, final BindingResult bindingResult ) {


		new UserValidator().validate( user, bindingResult ); // Use with @Valid in method argument to execute both annotations an custom class
		// NOTE: If not using @Valid annotation (with @InitBinder method) then use above line to execute my UserValidator class

		// This uses Spring Validation API and will validate the annotations in the domain User object automatically, remove @Valid when using this.
		//validator.validate( user, bindingResult );  


		User registered = null;

		if ( bindingResult.hasErrors() ) {
			return "registration";
		} else {
			registered = userService.registerNewUserAccount( user );


			// Assume the registration was successful at this point....
			//@TODO - SEE ABOUT ADDING TO HTTP SERVLET REQUEST ATTRIBUTE HERE TO PUT IN ATTRIBUTE FOR FORWARD TO CAN SEE IT ON HOMEPAGE ONLY WHEN COMING FROM HERE...
/*
CAN NOT USE THIS - AS FORWARDS A POST AND THE LOGIN PAGE IS A GET..WOULD HAVE TO DO A REDIRECT TO MAKE THAT HAPPEN...AS WITH A FORWARD (SERVER SIDE) THE ORIGINAL REQUEST STAYS INTACT...

		MIGHT HAVE TO DO A PROGRAMMACTIC LOGIN IF CONTINUE WITH THE FORWARDING WORKFLOW...WOULD FORWARD TO AN INTERNAL LOGIN CONTROLLER AND TAKE THE MODEL SENT ON (WHICH HAS BEEN REGISTERED) AND 
		LOG THE USER IN TO THE SYSTEM...THIS MIGHT BE THE NEATEST SOLUTION IF POSSIBLE...Otherwise making the user to login then after registering...which is the approach that will take if can not get programmatic login working...
		THEN WILL HAVE TO RE-WORK SOME OF THIS AGAIN LATER ONCE REMODEL THE DATABASE LATER FOR USING JPA BUT REALLY HAVE TO PUT THIS FULL SYSTEM IN PLACE...

		(DONE) : NOTE NOTE NOTE - FIRST READ UP ON THE VALIDATION STUFF FOR BINDING ERRORS AND SEE CAN FIND A WAY TO FORCE AN ERROR TO GET THAT PART
											OF THE WORKFLOW GOING...ONCE VALIDATION IS WORKING THEN CAN REUSE THAT FOR LOTS OF STUFF... 

*/


			return "forward:/athleteaccountv2/login";
		}


/*
		*** NOTE ---> KEEP THESE NOTES BELOW FOR COMMENT DOCUMENTATION ***
		WHAT I WANT FOR THIS IS AN ACTUUAL "FORWARD" REQUEST AS IT ALL STAYS SERVER SIDE.
		A REDIRECT TECHNICALLY RETURNS TO THE CLIENT BROWSER WITH A 302 AND ISSUES ANOTHER REQUEST - SO THE URLS CAN BE MONITORED...
		Like with the Redirect, the forward: prefix will be resolved by the UrlBasedViewResolver and its subclasses. Interally this will create 
		an InternalResourceView which does a RequestDispatcher.forward() to the new view...

		UNLIKE 'redirect' THE ATTRIBUTS IN MODEL DO NOT PROPAGATE AUTOMATICALLY IN CASE OF FORWARDING, so NEEDS A MANUAL APPROACH USING HTTPSERVLETREQUEST REQUEST VARIABLE...

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



