package org.training.siarhei_baradzionak.domain.controllers;



import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.siarhei_baradzionak.domain.beans.issue.Comment;
import org.training.siarhei_baradzionak.domain.beans.issue.Issue;
import org.training.siarhei_baradzionak.domain.beans.users.User;
import org.training.siarhei_baradzionak.domain.constants.ServletConstants;
import org.training.siarhei_baradzionak.domain.controllers.showtabs.AbstractBaseController;
import org.training.siarhei_baradzionak.domain.dao.impl.Issue.CommentService;
import org.training.siarhei_baradzionak.domain.dao.impl.Issue.IssueService;
import org.training.siarhei_baradzionak.domain.dao.interfaceDAO.Issue.ICommentDAO;
import org.training.siarhei_baradzionak.domain.dao.interfaceDAO.Issue.IIssueDAO;
import org.training.siarhei_baradzionak.exceptions.ExceptionDAO;



/**
 * Servlet implementation class AddIssueCommentController
 */

public class AddIssueCommentController extends AbstractBaseController {

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

		String commentStr = request.getParameter(ServletConstants.JSP_COMMENT);
		int issueId = Integer.parseInt(request.getParameter("hidden-issue-id"));

		String inputResult = getInputResult(commentStr);
		if (inputResult != null) {
			jump(ServletConstants.JUMP_EDIT_ISSUE_PAGE, inputResult, request,
					response);
			return;
		}

		try {
			User user = (User) session.getAttribute(ServletConstants.JSP_USER);

			Comment comment = new Comment();
			comment.setAddedBy(user);
			Calendar calendar = Calendar.getInstance();
			comment.setAddDate((Date) calendar.getTime());
			comment.setCommet(commentStr);
			IIssueDAO issueDAO = new IssueService();
			Issue issue = issueDAO.getIssueById(issueId);
			comment.setIssue(issue);

			// set comment in db
			ICommentDAO commentDAO = new CommentService();
			boolean isSet = commentDAO.addComment(comment);
			session.setAttribute(ServletConstants.JSP_COMMENT_ISSUE_ID, comment
					.getIssue().getId());
			if (isSet == true) {
				jumpError(ServletConstants.COMMENT_ADD_SUCCESSFULLY, request,
						response);
			} else {
				// user not add
				jumpError(ServletConstants.ERROR_COMMENT_NOT_ADD, request,
						response);
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
		jump(ServletConstants.JUMP_BEFORE_EDIT_ISSUE_CONTROLLER, message,
				request, response);
	}

	private String getInputResult(String comment) {
		if (comment == null || comment.equals("")) {
			return ServletConstants.ERROR_COMMENT_EMPTY;
		}
		return null;
	}

}
