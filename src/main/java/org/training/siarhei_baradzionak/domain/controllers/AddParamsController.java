package org.training.siarhei_baradzionak.domain.controllers;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.siarhei_baradzionak.domain.beans.issue.Priority;
import org.training.siarhei_baradzionak.domain.beans.issue.Resolution;
import org.training.siarhei_baradzionak.domain.beans.issue.Type;
import org.training.siarhei_baradzionak.domain.beans.project.BuildProject;
import org.training.siarhei_baradzionak.domain.beans.project.Project;
import org.training.siarhei_baradzionak.domain.beans.users.User;
import org.training.siarhei_baradzionak.domain.constants.ServletConstants;
import org.training.siarhei_baradzionak.domain.controllers.showtabs.AbstractBaseController;
import org.training.siarhei_baradzionak.domain.dao.impl.Issue.CommonDAO;
import org.training.siarhei_baradzionak.domain.dao.impl.usersDAO.UserImp;
import org.training.siarhei_baradzionak.domain.dao.interfaceDAO.IUsers.IUserDAO;
import org.training.siarhei_baradzionak.domain.dao.interfaceDAO.Issue.ITableDataDAO;
import org.training.siarhei_baradzionak.exceptions.ExceptionDAO;


/**
 * Servlet implementation class AddParamsController
 */

public class AddParamsController extends AbstractBaseController {

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

		String projectName = request.getParameter(ServletConstants.JSP_ADD_PROJECT_NAME);
		String projectDescr = request.getParameter(ServletConstants.JSP_ADD_PROJECT_DESCRIPTION);
		String projectBuild = request.getParameter(ServletConstants.JSP_ADD_PROJECT_BUILD);
		int projectManagerId = Integer.parseInt(request
				.getParameter(ServletConstants.JSP_ADD_PROJECT_MANAGERS));
		String addedType = request.getParameter(ServletConstants.JSP_ADD_TYPE);
		String addedResolution = request.getParameter(ServletConstants.JSP_ADD_RESOLUTION);
		String addedPriority = request.getParameter(ServletConstants.JSP_ADD_PRIORITY);


		String inputResult = getInputResult(projectName, projectDescr, projectBuild,
				addedType, addedResolution, addedPriority);
		if (inputResult != null) {
			jump(ServletConstants.JUMP_ADD_PARAMS_PAGE, inputResult, request,
					response);
			return;
		}
		
		Project project = null;
		Type type = null;
		Resolution resolution = null;
		Priority priority = null;
		
		try {
			ITableDataDAO tableDataDAO = new CommonDAO();
			if (!addedType.equals("")) {
				type = new Type();
				type.setType(addedType);
				tableDataDAO.setType(type);
			}
			if (!addedPriority.equals("")) {
				priority = new Priority();
				priority.setPriority(addedPriority);
				tableDataDAO.setPriority(priority);
			}
			if (!addedResolution.equals("")) {
				resolution = new Resolution();
				resolution.setResolution(addedResolution);
				tableDataDAO.setResolution(resolution);
			}
			if (!projectName.equals("") && !projectDescr.equals("")
					&& !projectBuild.equals("") && projectManagerId != 0) {
				project = new Project();
				project.setNameProject(projectName);
				project.setDescriptionProject(projectDescr);
				BuildProject buildFound = new BuildProject();
				buildFound.setNameBuild(projectBuild);
				project.getBuilds().add(buildFound);
				
				IUserDAO userDAO = new UserImp();
				User manager = userDAO.getUserById(projectManagerId);

				project.setManagerProject(manager);

				tableDataDAO.setProject(project);
			}
			
			jumpError(ServletConstants.PARAMS_ADD_SUCCESSFULLY, request,
					response);
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
		jump(ServletConstants.JUMP_ADD_PARAMS_PAGE, message,
				request, response);
	}

	private String getInputResult(String projectName, String projectDescr, 
			String projectBuild, String addedType, 
			String addedResolution, String addedPriority) {
		if ((projectName == null || projectName.equals(""))
				&& (projectDescr == null || projectDescr.equals(""))
				&& (projectBuild == null || projectBuild.equals(""))
				&& (addedType == null || addedType.equals(""))
				&& (addedResolution == null || addedResolution.equals(""))
				&& (addedPriority == null || addedPriority.equals(""))) {
			return ServletConstants.ERROR_ADD_PARAMS_EMPTY;
		}
		return null;
	}
}
