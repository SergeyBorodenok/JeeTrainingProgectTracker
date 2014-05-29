package org.training.issueTracker.sortedIssue;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.training.issueTracker.constants.ConstantsLogic;

public class SortedValidator {

	public static String getColum(HttpServletRequest request) {
		Enumeration<?> paramNames1 = request.getParameterNames();
		
		while (paramNames1.hasMoreElements()) {
			String paramName= (String)paramNames1.nextElement();
		
			if (ConstantsLogic.FIND_COLUM_SORT.equals(paramName) ) {
				String description=request.getParameterValues(paramName)[0];
				return description;
			}
			 
			
		}
		
		return null;
	}

	public static String getKind(HttpServletRequest request) {
		Enumeration<?> paramNames1 = request.getParameterNames();
		
		while (paramNames1.hasMoreElements()) {
			String paramName= (String)paramNames1.nextElement();
		
			if (ConstantsLogic.FIND_KIND_SORT.equals(paramName) ) {
				String description=request.getParameterValues(paramName)[0];
				return description;
			}
			 
			
		}
		
		return null;
	}
}
