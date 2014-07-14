package org.training.siarhei_baradzionak.domain.controllers.showtabs;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.siarhei_baradzionak.domain.beans.project.Project;
import org.training.siarhei_baradzionak.domain.beans.users.User;
import org.training.siarhei_baradzionak.domain.constants.ServletConstants;
import org.training.siarhei_baradzionak.domain.dao.impl.Issue.CommonDAO;
import org.training.siarhei_baradzionak.domain.dao.impl.usersDAO.UserImp;
import org.training.siarhei_baradzionak.domain.dao.interfaceDAO.IUsers.IUserDAO;
import org.training.siarhei_baradzionak.domain.dao.interfaceDAO.Issue.ITableDataDAO;
import org.training.siarhei_baradzionak.exceptions.ExceptionDAO;



/**
 * Servlet implementation class EditProjectController
 */

public class EditProjectController extends AbstractBaseController {
	
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
		
		String projectName = request.getParameter(ServletConstants
				.JSP_EDIT_PROJECT_NAME);
		String projectDescription = request.getParameter(ServletConstants
				.JSP_EDIT_PROJECT_DESCRIPTION);
		int projectManagerId = Integer.parseInt(request.getParameter(ServletConstants
				.JSP_EDIT_PROJECT_MANAGER));

		String inputResult = getInputResult(projectName, projectDescription);
		if(inputResult != null) {
			jump(ServletConstants.JUMP_EDIT_PROJECT_PAGE, inputResult, request, response);
			return;
		}
		
		Project project = (Project) session.getAttribute(ServletConstants.JSP_EDIT_PROJECT);
		
		if (project.getNameProject().equals(projectName)
				&& project.getDescriptionProject().equals(projectDescription)
				&& project.getManagerProject().getId() == projectManagerId) {
			jumpError(ServletConstants.PROJECT_EDIT_DATA_EQUAL, request, response);
			return;
		}
		
		try {
			//update project in db
			ITableDataDAO tableDataDAO = new CommonDAO();
			project.setNameProject(projectName);
			project.setDescriptionProject(projectDescription);
			
			//get user manager from db
			IUserDAO userDAO =new UserImp();
			User manager = userDAO.getUserById(projectManagerId);
			
			project.setManagerProject(manager);
			boolean isUpdated = tableDataDAO.updateProject(project);
			if (isUpdated == true) {
				session.removeAttribute(ServletConstants.JSP_EDIT_PROJECT_NAME);
				session.removeAttribute(ServletConstants.JSP_EDIT_PROJECT_DESCRIPTION);
				session.removeAttribute(ServletConstants.JSP_EDIT_PROJECT_BUILD);
				session.removeAttribute(ServletConstants.JSP_EDIT_PROJECT_MANAGER);
				jumpError(ServletConstants.PROJECT_UPDATE_SUCCESSFULLY, request, response);
			} else {
				//  project not update
				jumpError(ServletConstants.ERROR_PROJECT_NOT_UPDATE, request, response);
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
		jump(ServletConstants.JUMP_MAIN_PAGE, message, request, response);
	}

	private String getInputResult(String projectName, String projectDescription) {
		if(projectName == null || projectName.equals("")) {
			return ServletConstants.ERROR_PROJECT_NAME_EMPTY;
		}
		if(projectDescription == null || projectDescription.equals("")) {
			return ServletConstants.ERROR_PROJECT_DESCRIPTION_EMPTY;
		}
		return null;
	}

}
