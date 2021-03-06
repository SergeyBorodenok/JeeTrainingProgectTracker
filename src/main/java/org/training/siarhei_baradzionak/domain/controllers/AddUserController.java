package org.training.siarhei_baradzionak.domain.controllers;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.siarhei_baradzionak.domain.beans.users.RolesUser;
import org.training.siarhei_baradzionak.domain.beans.users.User;
import org.training.siarhei_baradzionak.domain.constants.ServletConstants;
import org.training.siarhei_baradzionak.domain.controllers.showtabs.AbstractBaseController;
import org.training.siarhei_baradzionak.domain.dao.impl.usersDAO.RoleImpl;
import org.training.siarhei_baradzionak.domain.dao.impl.usersDAO.UserImp;
import org.training.siarhei_baradzionak.domain.dao.interfaceDAO.IUsers.IRoleDAO;
import org.training.siarhei_baradzionak.domain.dao.interfaceDAO.IUsers.IUserDAO;
import org.training.siarhei_baradzionak.exceptions.ExceptionDAO;




public class AddUserController extends AbstractBaseController {

	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	
	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null) {
			jumpError(ServletConstants.ERROR_NULL_SESSION, request, response);
			return;
		}
		
		
		String firstName = request.getParameter(ServletConstants.JSP_FIRST_NAME);
		String lastName = request.getParameter(ServletConstants.JSP_LAST_NAME);
		String emailAddress = request.getParameter(ServletConstants.JSP_EMAIL_ADDRESS);
		String role = request.getParameter(ServletConstants.JSP_ROLE);
		String password = request.getParameter(ServletConstants.JSP_PASSWORD);
		String passwordConfirmation = request.getParameter(ServletConstants.JSP_PASSWORD_CONFIRMATION);
		
		
		String inputResult = getInputResult(firstName, lastName, emailAddress, role, password,
				passwordConfirmation);
		if(inputResult != null) {
			jump(ServletConstants.JUMP_ADD_USER_PAGE, inputResult, request, response);
			return;
		}
		
		User newUser = new User();
		
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setEmail(emailAddress);
		newUser.setPassword(password);
				
		try {
			//get Role from db
			IRoleDAO  roleDAO = new RoleImpl();
			RolesUser curRole = roleDAO.getExistRole(RolesUser.valueOf(role));
			newUser.setRole(curRole);
			
			//set user in db
			IUserDAO userDAO = new UserImp();
			boolean isSet = userDAO.addNewUser(newUser);
			if (isSet == true) {
				jumpError(ServletConstants.USER_ADD_SUCCESSFULLY, request, response);
			} else {
				//  user not add
				jumpError(ServletConstants.ERROR_USER_NOT_ADD, request, response);
			}
		} catch (ExceptionDAO e) {
			jumpError(e.getMessage(), request, response);
		}
	}

	protected void jump(String url, String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ServletConstants.KEY_ERROR_MESSAGE, message);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	protected void jumpPage(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		jump(url, ServletConstants.KEY_EMPTY, request, response);
	}

	protected void jumpError(String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		jump(ServletConstants.JUMP_ADD_USER_PAGE, message, request, response);
	}
	
	
	private String getInputResult(String firstName, String lastName, String emailAddress, 
			String role, String password, String passwordConfirmation) {
		if(firstName == null || firstName.equals("")) {
			return ServletConstants.ERROR_FIRST_NAME_EMPTY;
		}
		if (lastName == null || lastName.equals("")) {
			return ServletConstants.ERROR_LAST_NAME_EMPTY;
		}
		if (emailAddress == null || emailAddress.equals("")) {
			return ServletConstants.ERROR_EMAIL_EMPTY;
		}
		if (role == null || role.equals("")) {
			return ServletConstants.ERROR_ROLE_EMPTY;
		}
		if (password == null || password.equals("")) {
			return ServletConstants.ERROR_PASSWORD_EMPTY;
		}
		if (passwordConfirmation == null || passwordConfirmation.equals("")) {
			return ServletConstants.ERROR_PASSWORD_CONFIRM_EMPTY;
		}
		if (!password.equals(passwordConfirmation)) {
			return ServletConstants.ERROR_PASSWORDS_NOT_EQUAL;
		}
		return null;
	}
	

}
