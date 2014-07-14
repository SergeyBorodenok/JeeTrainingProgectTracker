package org.training.siarhei_baradzionak.domain.controllers;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.siarhei_baradzionak.domain.beans.project.BuildProject;
import org.training.siarhei_baradzionak.domain.beans.project.Project;
import org.training.siarhei_baradzionak.domain.constants.ServletConstants;
import org.training.siarhei_baradzionak.domain.controllers.showtabs.AbstractBaseController;
import org.training.siarhei_baradzionak.domain.dao.impl.Issue.CommonDAO;
import org.training.siarhei_baradzionak.domain.dao.interfaceDAO.Issue.ITableDataDAO;
import org.training.siarhei_baradzionak.exceptions.ExceptionDAO;



/**
 * Servlet implementation class BeforeSubmitIssueController
 */
public class BeforeSubmitIssueController extends AbstractBaseController {
	
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
		
		// get data from db
		ITableDataDAO tableDataDAO = new CommonDAO();
		try {
			session.setAttribute(ServletConstants.JSP_TYPES_LIST, tableDataDAO.getTypes());
			session.setAttribute(ServletConstants.JSP_STATUSES_LIST,tableDataDAO.getStatuses());
			session.setAttribute(ServletConstants.JSP_RESOLUTIONS_LIST, tableDataDAO.getResolutions());
			session.setAttribute(ServletConstants.JSP_PRIORITIES_LIST, tableDataDAO.getPriorities());
			session.setAttribute(ServletConstants.JSP_PROJECT_BUILDS_LIST, tableDataDAO.getBuild());
			session.setAttribute(ServletConstants.JSP_ASSIGNEES_LIST, tableDataDAO.getUsers());
			//get all projects
			List<Project> projects = tableDataDAO.getProjects();
			// set a Map builds depend on project. For javaScript functionality
			Map<Integer, Set<BuildProject>> buildsMapList = new HashMap<Integer,Set<BuildProject>>();
			for (Project project : projects) {
				buildsMapList.put(project.getId(), project.getBuilds());
			}			
			session.setAttribute(ServletConstants.JSP_PROJECTS_LIST, projects);
			session.setAttribute(ServletConstants.JSP_PROJECTS_CURRENT_BUILDS_LIST, buildsMapList);
			jumpPage(ServletConstants.JUMP_SUBMIT_ISSUE_PAGE, request, response);
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
		jump(ServletConstants.JUMP_SUBMIT_ISSUE_PAGE, message, request, response);
	}
	
	
}
