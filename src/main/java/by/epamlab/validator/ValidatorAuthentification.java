package by.epamlab.validator;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epamlab.beans.User;
import by.epamlab.constants.Constant;

public class ValidatorAuthentification {
	public static String getNameAttributeLogin(HttpServletRequest request) {
		Enumeration<?> paramNames1 = request.getParameterNames();
		
		while (paramNames1.hasMoreElements()) {
			String paramName= (String)paramNames1.nextElement();
			
			
			if ("login".equals(paramName)) {
				return request.getParameterValues(paramName)[0];
			}
			
		}
		return null;
	}
	public static String getNameAttributePassword(HttpServletRequest request) {
		Enumeration<?> paramNames1 = request.getParameterNames();
		
		while (paramNames1.hasMoreElements()) {
			String paramName= (String)paramNames1.nextElement();
			
			
			if ("password".equals(paramName)) {
				return request.getParameterValues(paramName)[0];
			}
			
		}
		return null;
	}
	public static String getUserMenu(List<User> users,String userName, String password){
		String user=null;
		for (User curUser : users) {
			if (curUser.getEmail().equals(userName) && curUser.getPassword().equals(password) ) {
				user=Constant.BEGINING_WELCOM_MESSAGE+curUser.getFirstName()+" "+curUser.getLastName()+Constant.ENDING_WELCOM_MESSAGE;
				if (curUser.getRole().equalsIgnoreCase(Constant.ROLE_USER)) {
					user=user+ Constant.USER_MENU;
							
				}
				if(curUser.getRole().equalsIgnoreCase(Constant.ROLE_ADMIN)){
					user=user+ Constant.ADMIN_MENU;
				}
			
			}
		}
		return user;
		
	}
}
