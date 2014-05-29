package org.training.issueTracker.authentification;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.training.issueTracker.constants.ConstantsLogic;

public class ValidatorAuthentification {
	public static String getNameAttributeLogin(HttpServletRequest request) {
		Enumeration<?> paramNames1 = request.getParameterNames();
		
		while (paramNames1.hasMoreElements()) {
			String paramName= (String)paramNames1.nextElement();
			
			
			if (ConstantsLogic.FIND_PARAM_LOGIN.equals(paramName)) {
				return request.getParameterValues(paramName)[0];
			}
			
		}
		return null;
	}

	public static String getNameAttributePassword(HttpServletRequest request) {
		Enumeration<?> paramNames1 = request.getParameterNames();
		
		while (paramNames1.hasMoreElements()) {
			String paramName= (String)paramNames1.nextElement();
			
			
			if (ConstantsLogic.FIND_PARAM_PASSWORD.equals(paramName)) {
				return request.getParameterValues(paramName)[0];
			}
			
		}
		return null;
	}
}
