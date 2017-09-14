package com.fireduptech.springsecurity.web;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import java.security.Principal;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;

//import org.apache.commons.lang3.math.NumberUtils;  - This is DEPRECATED
import org.apache.commons.lang.math.NumberUtils;

import com.fireduptech.springsecurity.domain.AthleteAccountDetails;
import com.fireduptech.springsecurity.service.AthleteAccountService;

@Controller
@RequestMapping( value = "/athleteAccount" )
public class AthleteAccountController {


	@Autowired
	private AthleteAccountService athleteAccountService;

	/*
	 * This will list all the Athlete Accounts ONLY for ROLE_ADMIN
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping( value= "/list", method = RequestMethod.GET )
	public ModelAndView listAthleteAccounts( Principal principal ) {

		Map<String, List<AthleteAccountDetails>> modelData = new HashMap<String, List<AthleteAccountDetails>>();

		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

		Iterator<GrantedAuthority> iterator = authorities.iterator();

		if ( iterator.hasNext() ) {

			//String role = ((GrantedAuthority) iterator.next()).getAuthority();  This compiled but with a warning that cast (GrantedAuthority) was redundant
			String role = (iterator.next()).getAuthority();
			if ( role.equalsIgnoreCase( "ROLE_ADMIN" ) ) {

				// acdList - athlete account details list
				modelData.put( "acdList", athleteAccountService.getAllAthleteAccounts() );
			}
		}

		return new ModelAndView( "athleteAccountsList", modelData );

	}	// End of method listAthleteAccounts()...



	@RequestMapping( params = "acAction=createACForm", method = RequestMethod.POST )
	public ModelAndView showOpenAthleteAccountForm() {

		AthleteAccountDetails athleteAccountDetails = new AthleteAccountDetails();
		athleteAccountDetails.setEmail( "You must enter a valid email" );
		ModelMap modelData = new ModelMap();
		modelData.addAttribute( athleteAccountDetails );
		return new ModelAndView( "createAthleteAccountForm", modelData );

	}	// End of method showOpenAthleteAccountForm()...



	@RequestMapping( params = "acAction=create", method = RequestMethod.POST )
	public ModelAndView createAthleteAccount( @RequestParam Map<String, String> params, Principal principal ) {

		String accountType = params.get("accountType");
		String name = params.get("name");
		String email = params.get("email");
		String primaryActivity = params.get("primaryActivity");

		Map<String, Object> modelData = new HashMap<String, Object>();

		if ( email == null || "".equalsIgnoreCase(email) ) {
			modelData.put( "error.email", "must not be blank" );
		} else if ( !email.contains( "@" ) ) {
			modelData.put( "error.email", "invalid email address structure" );
		}

		AthleteAccountDetails athleteAccountDetails = new AthleteAccountDetails();
		athleteAccountDetails.setAccountType( accountType );
		athleteAccountDetails.setName( name );
		athleteAccountDetails.setEmail( email );
		athleteAccountDetails.setPrimaryActivity( primaryActivity );
		athleteAccountDetails.setAccountId( principal.getName() );

		if ( modelData.size() > 0 ) { // This means that there are Validation Errors
			modelData.put( "athleteAccountDetails", athleteAccountDetails );
			return new ModelAndView( "createAthleteAccountForm", modelData );
		} else {
			athleteAccountService.saveAthleteAccount( athleteAccountDetails );
			return new ModelAndView( "redirect:/athleteAccount/list" );
		}

	} // End of method creatAthleteAccount()...


	@RequestMapping( params = "acAction=edit", method = RequestMethod.POST )
	public ModelAndView editAthleteAccount( @RequestParam MultiValueMap<String, String> params, Principal principal ) {

		String id = params.get("id").get(0);
		String accountType = params.get("accountType").get(0);
		String name = params.get("name").get(0);
		String email = params.get("email").get(0);
		String primaryActivity = params.get("primaryActivity").get(0);

		Map<String, Object> modelData = new HashMap<String, Object>();

		if ( email == null || "".equalsIgnoreCase(email) ) {
			modelData.put( "error.email", "must not be blank" );
		} else if ( !email.contains( "@" ) ) {
			modelData.put( "error.email", "invalid email address structure" );
		}

		if ( !NumberUtils.isNumber(id) ) {
			modelData.put( "error.id", "invalid Id" );
		}

		AthleteAccountDetails athleteAccountDetails = new AthleteAccountDetails();
		athleteAccountDetails.setId( Integer.parseInt( id ) );
		athleteAccountDetails.setAccountType( accountType );
		athleteAccountDetails.setName( name );
		athleteAccountDetails.setEmail( email );
		athleteAccountDetails.setPrimaryActivity( primaryActivity );
		athleteAccountDetails.setAccountId( principal.getName() );

		if ( modelData.size() > 0 ) {	// This means that there are Validation Errors
			modelData.put( "athleteAccountDetails", athleteAccountDetails );
			return new ModelAndView( "editAthleteAccountForm", modelData );
		} else {
			athleteAccountService.editAthleteAccount( athleteAccountDetails );
			return new ModelAndView( "redirect/athleteAccount/list" );
		}

	} // End of method editAthleteAccount()...


	@RequestMapping( params = "acAction=close", method = RequestMethod.GET )
	public String closeAthleteAccount( @RequestParam( value = "athleteAccountId" ) int aaId ) {
		athleteAccountService.closeAthleteAccount( aaId );
		return "redirect:/athleteAccount/list";
	}


	@RequestMapping( params = "acAction=view", method = RequestMethod.GET )
	public ModelAndView viewAthleteAccountDetails( HttpServletRequest request ) {

		AthleteAccountDetails athleteAccountDetails = athleteAccountService.getAthleteAccount( 
			Integer.parseInt( request.getParameter("athleteAccountId") ) );

		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute( athleteAccountDetails );
		return new ModelAndView( "editAthleteAccountForm", modelMap );

	}	// End of method viewAthleteAccountDetails()...



	@ExceptionHandler
	public ModelAndView handleException( Exception ex ) {
		ModelAndView mav = new ModelAndView( "error", "msg", ex.getMessage() );
		return mav;
	}

} // End of class AthleteAccountController...



