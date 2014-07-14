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



/**
 * Servlet implementation class EditUserController
 */
public class EditUserController extends AbstractBaseController {
	
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
		
		User editUser = null;
		User user = null;
		
		user = (User) session.getAttribute(ServletConstants.JSP_USER);
		
		if (session.getAttribute(ServletConstants.JSP_EDIT_USER_BY_ID) == null) {
			editUser = (User) session.getAttribute(ServletConstants.JSP_USER);
		} else {
			editUser = (User) session.getAttribute(ServletConstants.JSP_EDIT_USER_BY_ID);
			session.removeAttribute(ServletConstants.JSP_EDIT_USER_BY_ID);
		}
		
		String firstName = request.getParameter(ServletConstants.JSP_FIRST_NAME);
		String lastName = request.getParameter(ServletConstants.JSP_LAST_NAME);
		String emailAddress = request.getParameter(ServletConstants.JSP_EMAIL_ADDRESS);
		
		String inputResult = getInputResult(firstName, lastName, emailAddress);
		if(inputResult != null) {
			jump(ServletConstants.JUMP_EDIT_USER_PAGE, inputResult, request, response);
			return;
		}
		
		editUser.setFirstName(firstName);
		editUser.setLastName(lastName);
		editUser.setEmail(emailAddress);

		if (user.getRole().equals(RolesUser.ADMINISTRATOR.toString())) {
			String role = request.getParameter(ServletConstants.JSP_ROLE);
			try {
				IRoleDAO roleDAO = new RoleImpl();
				editUser.setRole(roleDAO.getExistRole(RolesUser.valueOf(role)));
			} catch (Exception e) {
				System.out.println("eror in get role in edit User controller " + e);
			}
			
		}

		
		try {
			//update user in db
			IUserDAO userDAO = new UserImp();
			boolean isUpdated = userDAO.updateUser(editUser);
			session.removeAttribute(ServletConstants.JSP_EDIT_DIFF_USER_BY_ID);
			if (isUpdated == true) {
				jumpError(ServletConstants.USER_UPDATE_SUCCESSFULLY, request, response);
			} else {
				//  user not found
				jumpError(ServletConstants.ERROR_USER_NOT_UPDATE, request, response);
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
		jump(ServletConstants.JUMP_INDEX_PAGE, message, request, response);
	}

	private String getInputResult(String firstName, String lastName, String emailAddress) {
		if(firstName == null || firstName.equals("")) {
			return ServletConstants.ERROR_FIRST_NAME_EMPTY;
		}
		if (lastName == null || lastName.equals("")) {
			return ServletConstants.ERROR_LAST_NAME_EMPTY;
		}
		if (emailAddress == null || emailAddress.equals("")) {
			return ServletConstants.ERROR_EMAIL_EMPTY;
		}
		return null;
	}

}
