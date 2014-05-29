package org.training.issueTracker.editIssue;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.training.issueTracker.abstractController.AbstractBaseController;
import org.training.issueTracker.beans.User;
import org.training.issueTracker.constants.ConstantsController;
import org.training.issueTracker.exception.DaoException;
import org.training.issueTracker.modelTask.ITaskDAO;
import org.training.issueTracker.modelTask.TaskFactory;

/**
 * Servlet implementation class UpdateEditIssueController
 */
public class UpdateEditIssueController extends AbstractBaseController {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UpdateEditIssueController() {
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String path=getServletContext().getRealPath("/")+ConstantsController.SERVLET_PATH;
		User curUser=(User) request.getSession().getAttribute(ConstantsController.ATTRIBUTE_USER);
		int issue_id=EditIssueValidator.getIssueId(request);
		String summary=EditIssueValidator.getSummary(request);
		String description=EditIssueValidator.getDescription(request);
		String status=EditIssueValidator.getStatus(request);
		String type=EditIssueValidator.getTypes(request);
		String priority=EditIssueValidator.getPriority(request);
		String project=EditIssueValidator.getProject(request);
		String build=EditIssueValidator.getBuild(request);
		String userLoginAssignee=EditIssueValidator.getUserLogin(request);
		String resolution=EditIssueValidator.getResolution(request);
		ITaskDAO task= TaskFactory.getClassFromFactory(path);
		
		try {
			task.updateIssue(issue_id, curUser, summary, description, status, type, priority, project, build, userLoginAssignee, resolution);
		} catch (DaoException e) {
			e.printStackTrace();
			request.setAttribute(ConstantsController.ATTRIBUTE_NUMBER_ISSUE, issue_id);
			jump(ConstantsController.JUMP_EDIT_ISSUE_CONTROLLER,e.getMessage() , request, response);
			
			return;
		}
		request.setAttribute(ConstantsController.ATTRIBUTE_NUMBER_ISSUE, issue_id);
		request.setAttribute(ConstantsController.ATTRIBUTE_SUCCESS, ConstantsController.MESSAGE_SUCCESS);
		jump(ConstantsController.JUMP_EDIT_ISSUE_CONTROLLER, request, response);
	}

	protected void jump(String url, String ErrorMessage, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute(ConstantsController.ATTRIBUTE_PROBLEM_EDIT, ErrorMessage);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
