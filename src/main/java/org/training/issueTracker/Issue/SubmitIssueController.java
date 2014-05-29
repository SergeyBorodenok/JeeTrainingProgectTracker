package org.training.issueTracker.Issue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;











import org.training.issueTracker.abstractController.AbstractBaseController;
import org.training.issueTracker.beans.Build_Found;
import org.training.issueTracker.beans.Project;
import org.training.issueTracker.beans.User;
import org.training.issueTracker.constants.ConstantsController;
import org.training.issueTracker.exception.DaoException;
import org.training.issueTracker.modelTask.ITaskDAO;
import org.training.issueTracker.modelTask.TaskFactory;

/**
 * Servlet implementation class SubmitIssueController
 */
public class SubmitIssueController extends AbstractBaseController  {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitIssueController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=getServletContext().getRealPath("/")+"WEB-INF\\classes\\";
		ITaskDAO task= TaskFactory.getClassFromFactory(path);
		List<String> tempStatus = new ArrayList<String>();
		List<String> status=null;
		
		List<String> types=null;
		List<String> priority=null;
		List<Project> project=null;
		List<Build_Found> builds=null;
		List<User>  users=null;
		try {
			status=task.getAllStatus();
			
			types = task.getAllType();
			priority=task.getAllPriority();
			project=task.getAllProgect();
			builds=task.getAllBuild();
			users=task.getAllUser();
			for(String statuses: status){
				   if(ConstantsController.STATUS_NEW.equals(statuses) || ConstantsController.STATUS_ASSIGNED.equals(statuses) ){
				    tempStatus.add(statuses);
				    
				   }
			  }
			request.setAttribute(ConstantsController.ATTRIBUTE_ALL_STATUS, tempStatus);
			request.setAttribute(ConstantsController.ATTRIBUTE_ALL_TYPE, types);
			request.setAttribute(ConstantsController.ATTRIBUTE_ALL_PRIORITY, priority);
			request.setAttribute(ConstantsController.ATTRIBUTE_ALL_PROJECT, project);
			request.setAttribute(ConstantsController.ATTRIBUTE_ALL_BUILD, builds);
			request.setAttribute(ConstantsController.ATTRIBUTE_ALL_USERS, users);
			jump(ConstantsController.JUMP_SUBMIT_ISSUE_PAGE, request, response);
		} catch (DaoException e) {
			jump(ConstantsController.JUMP_SUBMIT_ISSUE_PAGE,e.getMessage() , request, response);
		}
				
		
		
	}

	protected void jump(String url, String ErrorMessage, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute(ConstantsController.ATTRIBUTE_ERROR_ADD, ErrorMessage);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
