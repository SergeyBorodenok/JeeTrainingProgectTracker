package org.training.issueTracker.Issue;


import java.util.Enumeration;











import javax.servlet.http.HttpServletRequest;

import org.training.issueTracker.constants.ConstantsLogic;


public class SubmitValidator {
	public static String getSummary( HttpServletRequest request) {
		Enumeration<?> paramNames1 = request.getParameterNames();
		
		while (paramNames1.hasMoreElements()) {
			String paramName= (String)paramNames1.nextElement();
		
			if (ConstantsLogic.FIND_SUMMARY.equals(paramName) ) {
				String summary=request.getParameterValues(paramName)[0];
				return summary;
			}
			 
			
		}
		
		return null;
	}

	public static String getDescription(HttpServletRequest request) {
		Enumeration<?> paramNames1 = request.getParameterNames();
		
		while (paramNames1.hasMoreElements()) {
			String paramName= (String)paramNames1.nextElement();
		
			if (ConstantsLogic.FIND_DESCRIPTION.equals(paramName) ) {
				String description=request.getParameterValues(paramName)[0];
				return description;
			}
			 
			
		}
		
		return null;
	}

	public static String getStatus(HttpServletRequest request) {
		Enumeration<?> paramNames1 = request.getParameterNames();
		
		while (paramNames1.hasMoreElements()) {
			String paramName= (String)paramNames1.nextElement();
		
			if (ConstantsLogic.FIND_STATUS.equals(paramName) ) {
				String status=request.getParameterValues(paramName)[0];
				return status;
			}
			 
			
		}
		
		return null;
	}

	public static String getTypes(HttpServletRequest request) {
		Enumeration<?> paramNames1 = request.getParameterNames();
		
		while (paramNames1.hasMoreElements()) {
			String paramName= (String)paramNames1.nextElement();
		
			if (ConstantsLogic.FIND_TYPE.equals(paramName) ) {
				String types=request.getParameterValues(paramName)[0];
				return types;
			}
			 
			
		}
		
		return null;
	}

	public static String getPriority(HttpServletRequest request) {
		Enumeration<?> paramNames1 = request.getParameterNames();
		
		while (paramNames1.hasMoreElements()) {
			String paramName= (String)paramNames1.nextElement();
		
			if (ConstantsLogic.FIND_PRIORITY.equals(paramName) ) {
				String priority=request.getParameterValues(paramName)[0];
				return priority;
			}
			 
			
		}
		
		return null;
	}

	public static String getProject(HttpServletRequest request) {
		Enumeration<?> paramNames1 = request.getParameterNames();
		
		while (paramNames1.hasMoreElements()) {
			String paramName= (String)paramNames1.nextElement();
		
			if (ConstantsLogic.FIND_PROJECT.equals(paramName) ) {
				String project=request.getParameterValues(paramName)[0];
				return project;
			}
			 
			
		}
		
		return null;
	}

	public static String getBuild(HttpServletRequest request) {
		Enumeration<?> paramNames1 = request.getParameterNames();
		
		while (paramNames1.hasMoreElements()) {
			String paramName= (String)paramNames1.nextElement();
		
			if (ConstantsLogic.FIND_BUILDS.equals(paramName) ) {
				String builds=request.getParameterValues(paramName)[0];
				return builds;
			}
			 
			
		}
		
		return null;
	}

	public static String getUserLogin(HttpServletRequest request) {
		Enumeration<?> paramNames1 = request.getParameterNames();
		
		while (paramNames1.hasMoreElements()) {
			String paramName= (String)paramNames1.nextElement();
		
			if (ConstantsLogic.FIND_USERS_LOGIN.equals(paramName) ) {
				String userLogin=request.getParameterValues(paramName)[0];
				return userLogin;
			}
			 
			
		}
		
		return null;
	}

	
}
