package by.epamlab.validator;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import by.epamlab.constants.Constant;



public class ValidatorWelcomPage {
	
	public static String getNameAttributeUser(HttpServletRequest request) {
		Enumeration<?> paramNames2 = request.getAttributeNames();
		
		while (paramNames2.hasMoreElements()) {
			String paramName= (String)paramNames2.nextElement();
			
			
			if ("user".equals(paramName)) {
				
				return (String) request.getAttribute(Constant.ATTRIBUTE_USERS_MENU);
			}
			
		}
		
		return null;
	}

	public static String getNameAttributeProblemWithAuthentification(
			HttpServletRequest request) {
		Enumeration<?> paramNames2 = request.getAttributeNames();
		
		while (paramNames2.hasMoreElements()) {
			String paramName= (String)paramNames2.nextElement();
			
			
			if ("problemAuthentification".equals(paramName)) {
				
				return (String) request.getAttribute(Constant.ATTRIBUTE_PROBLEM_AUTHENTIFICATION);
			}
			
		}
		return null;
	}
	
}
