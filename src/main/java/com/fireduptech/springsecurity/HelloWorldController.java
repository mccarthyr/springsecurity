package com.fireduptech.springsecurity;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloWorldController implements Controller {

	@Override
	public ModelAndView handleRequest( HttpServletRequest request,
		 HttpServletResponse response ) throws Exception {

		Map<String, String> modelData = new HashMap<String, String>();
		modelData.put( "msg", "Hello logged in user, welcome to the Spring Security World!" );
		return new ModelAndView( "helloworld", modelData );
	}

}