package org.training.siarhei_baradzionak.domain.controllers;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.siarhei_baradzionak.domain.beans.issue.Issue;
import org.training.siarhei_baradzionak.domain.beans.users.RolesUser;
import org.training.siarhei_baradzionak.domain.beans.users.User;
import org.training.siarhei_baradzionak.domain.constants.ServletConstants;
import org.training.siarhei_baradzionak.domain.controllers.showtabs.AbstractBaseController;
import org.training.siarhei_baradzionak.domain.dao.impl.Issue.DefaultTable;
import org.training.siarhei_baradzionak.domain.dao.impl.Issue.IssueService;
import org.training.siarhei_baradzionak.exceptions.ExceptionDAO;




/**
 * Servlet implementation class SessionController
 */
public class SessionController extends AbstractBaseController {
	
	private static final long serialVersionUID = 1L;
	
	private static boolean isTableNotCreated = true;
       
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
		try{
		HttpSession session= request.getSession(false);
		
		if (session == null) {
			session= request.getSession(true);
			session.setAttribute(ServletConstants.JSP_USER, new User());
			return;
		}
		
		//create default data in the tables
		if (isTableNotCreated) {
			new DefaultTable();
			isTableNotCreated = false;
		}
		
		// get all issues from db
		List<Issue> issuesList = new ArrayList<Issue>();
		
			User curUser = (User) session.getAttribute(ServletConstants.JSP_USER);
			if (curUser == null || curUser.getRole()
					.equals(RolesUser.GUEST.toString())) {
				issuesList = new IssueService().getAllIssues();
			} else { 
				if (curUser.getRole().
						equals(RolesUser.USER.toString()) 
						|| curUser.getRole()
						.equals(RolesUser.ADMINISTRATOR.toString())) { 
					issuesList = new IssueService().getUserIssues(curUser.getId());
				}
			}
			
			//write data in session
			session.setAttribute(ServletConstants.JSP_ISSUES_LIST, issuesList);
		} catch (ExceptionDAO e) {
			jumpError(e.getMessage(), request, response);
		}

		jumpPage(ServletConstants.JUMP_MAIN_PAGE, request, response);
	}

	
	// jump to the jsp page
	protected void jump(String url, String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ServletConstants.KEY_ERROR_MESSAGE, message);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	// jump to the next valid page
	protected void jumpPage(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		jump(url, ServletConstants.KEY_EMPTY, request, response);
	}
	
	protected void jumpError(String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		jump(ServletConstants.JUMP_MAIN_PAGE, message, request, response);
	}
	
}
