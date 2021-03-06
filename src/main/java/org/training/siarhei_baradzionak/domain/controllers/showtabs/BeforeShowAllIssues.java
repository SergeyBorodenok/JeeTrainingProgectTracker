package org.training.siarhei_baradzionak.domain.controllers.showtabs;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.siarhei_baradzionak.domain.constants.ServletConstants;
import org.training.siarhei_baradzionak.domain.dao.impl.Issue.IssueService;
import org.training.siarhei_baradzionak.domain.dao.interfaceDAO.Issue.IIssueDAO;
import org.training.siarhei_baradzionak.exceptions.ExceptionDAO;



/**
 * Servlet implementation class BeforeShowAllIssues
 */

public class BeforeShowAllIssues extends AbstractBaseController {
	
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
		try {
			IIssueDAO issueDAO = new IssueService();
			session.setAttribute(ServletConstants.JSP_ALL_ISSUES_LIST,
					issueDAO.getAllIssues());
			jumpPage(ServletConstants.JUMP_SHOW_ALL_ISSUES_PAGE, request, response);
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

}
