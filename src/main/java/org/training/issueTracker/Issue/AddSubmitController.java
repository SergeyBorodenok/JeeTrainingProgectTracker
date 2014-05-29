package org.training.issueTracker.Issue;

import java.io.IOException;











import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issueTracker.abstractController.AbstractBaseController;
import org.training.issueTracker.beans.User;
import org.training.issueTracker.constants.ConstantsController;
import org.training.issueTracker.exception.DaoException;
import org.training.issueTracker.modelTask.ITaskDAO;
import org.training.issueTracker.modelTask.TaskFactory;





/**
 * Servlet implementation class AddSubmitController
 */
public class AddSubmitController extends AbstractBaseController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSubmitController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=getServletContext().getRealPath("/")+ConstantsController.SERVLET_PATH;
		User curUser=(User) request.getSession().getAttribute(ConstantsController.ATTRIBUTE_USER);
		String summary=SubmitValidator.getSummary(request);
		String description=SubmitValidator.getDescription(request);
		String status=SubmitValidator.getStatus(request);
		String type=SubmitValidator.getTypes(request);
		String priority=SubmitValidator.getPriority(request);
		String project=SubmitValidator.getProject(request);
		String build=SubmitValidator.getBuild(request);
		
		String userLoginAssignee=SubmitValidator.getUserLogin(request);
		ITaskDAO task= TaskFactory.getClassFromFactory(path);
		if(((ConstantsController.STATUS_NEW.equals(status) && ConstantsController.USER_ASSIGNED_EMPTY.equals(userLoginAssignee)) || 
				(ConstantsController.STATUS_ASSIGNED.equals(status) && !ConstantsController.USER_ASSIGNED_EMPTY.equals(userLoginAssignee))) && !ConstantsController.EMPTY.equals(summary) && !ConstantsController.PROJECT_SELECT.equals(project)){
		
		try {
			task.insertIssue(curUser, summary, description, status, type, priority, project, build, userLoginAssignee);
		} catch (DaoException e) {
			
			jump(ConstantsController.JUMP_SUBMIT_ISSUE_CONTROLLER,e.getMessage() , request, response);
			return ;
		}
		request.setAttribute(ConstantsController.ATTRIBUTE_SUCCESS, ConstantsController.MESSAGE_SUCCESS_ADD_ISSUE);
		jump(ConstantsController.JUMP_SUBMIT_ISSUE_CONTROLLER, request, response);
		}else{
			jump(ConstantsController.JUMP_SUBMIT_ISSUE_CONTROLLER, ConstantsController.ERROR_MESSAGE_ADD_ISSUE, request, response);
		}
	}
	protected void jump(String url, String ErrorMessage, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute(ConstantsController.ATTRIBUTE_ERROR_ADD, ErrorMessage);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
	

}
