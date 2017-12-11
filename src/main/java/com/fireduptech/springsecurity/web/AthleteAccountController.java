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

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.apache.commons.lang.math.NumberUtils;

import com.fireduptech.springsecurity.domain.AthleteAccount;
import com.fireduptech.springsecurity.service.AthleteAccountService;


@Controller
@RequestMapping( value = "/athleteAccount" )
public class AthleteAccountController {


	@Autowired
	private AthleteAccountService athleteAccountService;

	/*
	 * This will list all the Athlete Accounts only for ROLE_ADMIN
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping( value= "/list", method = RequestMethod.GET )
	public ModelAndView listAthleteAccounts( Principal principal ) {

		Map<String, List<AthleteAccount>> modelData = new HashMap<String, List<AthleteAccount>>();

		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

		Iterator<GrantedAuthority> iterator = authorities.iterator();

		if ( iterator.hasNext() ) {

			/*
			String role = (iterator.next()).getAuthority();
			if ( role.equalsIgnoreCase( "ROLE_ADMIN" ) ) {
			*/
				// acdList - athlete account details list
				modelData.put( "acdList", athleteAccountService.getAllAthleteAccounts() );
			/*		
			} */
		}

		return new ModelAndView( "athleteAccountsList", modelData );
	}



	@RequestMapping( params = "acAction=createACForm", method = RequestMethod.POST )
	public ModelAndView showOpenAthleteAccountForm() {

		AthleteAccount athleteAccount = new AthleteAccount();
		athleteAccount.setEmail( "You must enter a valid email" );
		ModelMap modelData = new ModelMap();
		modelData.addAttribute( athleteAccount );

		return new ModelAndView( "createAthleteAccountForm", modelData );
	}



	@RequestMapping( params = "acAction=create", method = RequestMethod.POST )
	public ModelAndView createAthleteAccount( @RequestParam Map<String, String> params, Principal principal ) {

		String email = params.get("email");
		String firstName = params.get("firstName");
		String lastName = params.get("lastName");
		String accountType = params.get("accountType");
		String primaryActivity = params.get("primaryActivity");

		Map<String, Object> modelData = new HashMap<String, Object>();

		if ( !accountType.equalsIgnoreCase("freemium") && !accountType.equalsIgnoreCase("premium") ) {
			modelData.put( "error.accountType", "enter a valid account type" );
		} else if ( accountType == null || "".equalsIgnoreCase( accountType ) ) {
			modelData.put( "error.accountType", "must not be blank" );
		}

		if ( firstName == null || "".equalsIgnoreCase( firstName ) ) {
			modelData.put( "error.firstName", "enter a first name" );
		}

		if ( lastName == null || "".equalsIgnoreCase( lastName ) ) {
			modelData.put( "error.lastName", "enter a last name" );
		}

		if ( !primaryActivity.equalsIgnoreCase("cycling") && !primaryActivity.equalsIgnoreCase("running") ) {
			modelData.put( "error.primaryActivity", "enter a valid primary activity" );
		} else if ( primaryActivity == null || "".equalsIgnoreCase( primaryActivity ) ) {
			modelData.put( "error.primaryActivity", "must not be blank" );
		}


		if ( email == null || "".equalsIgnoreCase(email) ) {
			modelData.put( "error.email", "must not be blank" );
		} else if ( !email.contains( "@" ) ) {
			modelData.put( "error.email", "invalid email address structure" );
		}

		AthleteAccount athleteAccount = new AthleteAccount();
		athleteAccount.setFirstName( firstName );
		athleteAccount.setLastName( lastName );
		athleteAccount.setEmail( email );
		athleteAccount.setAccountType( accountType );
		athleteAccount.setPrimaryActivity( primaryActivity );


		if ( modelData.size() > 0 ) { // This means that there are Validation Errors
			modelData.put( "athleteAccount", athleteAccount );
			return new ModelAndView( "createAthleteAccountForm", modelData );
		} else {
			athleteAccountService.saveAthleteAccount( athleteAccount );	
			return new ModelAndView( "redirect:/athleteaccountv2/athleteAccount/list" );
		}
	}



	@RequestMapping( params = "acAction=edit", method = RequestMethod.POST )
	public ModelAndView editAthleteAccount( @RequestParam MultiValueMap<String, String> params, Principal principal ) {

		String id = params.get("id").get(0);
		String email = params.get("email").get(0);
		String firstName = params.get("firstName").get(0);
		String lastName = params.get("lastName").get(0);
		String accountType = params.get("accountType").get(0);
		String primaryActivity = params.get("primaryActivity").get(0);

		Map<String, Object> modelData = new HashMap<String, Object>();

		if ( !accountType.equalsIgnoreCase("freemium") && !accountType.equalsIgnoreCase("premium") ) {
			modelData.put( "error.accountType", "enter a valid account type" );
		} else if ( accountType == null || "".equalsIgnoreCase( accountType ) ) {
			modelData.put( "error.accountType", "must not be blank" );
		}

		if ( firstName == null || "".equalsIgnoreCase( firstName ) ) {
			modelData.put( "error.firstName", "enter a first name" );
		}

		if ( lastName == null || "".equalsIgnoreCase( lastName ) ) {
			modelData.put( "error.lastName", "enter a last name" );
		}


		if ( !primaryActivity.equalsIgnoreCase("cycling") && !primaryActivity.equalsIgnoreCase("running") ) {
			modelData.put( "error.primaryActivity", "enter a valid primary activity" );
		} else if ( primaryActivity == null || "".equalsIgnoreCase( primaryActivity ) ) {
			modelData.put( "error.primaryActivity", "must not be blank" );
		}


		if ( email == null || "".equalsIgnoreCase(email) ) {
			modelData.put( "error.email", "must not be blank" );
		} else if ( !email.contains( "@" ) ) {
			modelData.put( "error.email", "invalid email address structure" );
		}


		AthleteAccount athleteAccount = new AthleteAccount();

		athleteAccount.setId( Integer.parseInt( id ) );
		athleteAccount.setEmail( email );
		athleteAccount.setFirstName( firstName );
		athleteAccount.setLastName( lastName );
		athleteAccount.setPrimaryActivity( primaryActivity );
		athleteAccount.setAccountType( accountType );


		if ( modelData.size() > 0 ) {	// This means that there are Validation Errors
			modelData.put( "athleteAccount", athleteAccount );
			return new ModelAndView( "editAthleteAccountForm", modelData );
		} else {
			athleteAccountService.editAthleteAccount( athleteAccount );
			return new ModelAndView( "redirect:/athleteaccountv2/athleteAccount/list" );																
		}
	}



	@RequestMapping( params = "acAction=close", method = RequestMethod.GET )
	public String closeAthleteAccount( @RequestParam( value = "athleteAccountId" ) int aaId ) {

		athleteAccountService.closeAthleteAccount( aaId );

		return "redirect:/athleteaccountv2/athleteAccount/list";
	}



	@RequestMapping( params = "acAction=view", method = RequestMethod.GET )
	public ModelAndView viewAthleteAccount( @RequestParam( value = "athleteAccountId" ) int aaId ) {

		AthleteAccount athleteAccount = athleteAccountService.getAthleteAccount( aaId );

		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute( athleteAccount );

		return new ModelAndView( "editAthleteAccountForm", modelMap );
	}



	@RequestMapping( params = "acAction=provideAccessToAdmin", method = RequestMethod.GET )
	public String provideAccessToAdmin( HttpServletRequest request, RedirectAttributes redirectAttr ) {

		int athleteAccountId = Integer.parseInt( request.getParameter( "athleteAccountId" ) );
		athleteAccountService.provideAccessToAdmin( athleteAccountId );
		redirectAttr.addAttribute( "msg", "Admin access provided to the athlete account with id: " + athleteAccountId );

		return "redirect:/athleteaccountv2/athleteAccount/list";
	}



	@ExceptionHandler
	public ModelAndView handleException( Exception ex ) {
		ModelAndView mav = new ModelAndView( "error", "msg", "--FROM ATHLETE CONTROLLER: --> " + ex.getMessage() );

		return mav;
	}

}
