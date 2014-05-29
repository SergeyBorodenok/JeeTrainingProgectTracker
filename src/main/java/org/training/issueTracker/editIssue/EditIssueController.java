package org.training.issueTracker.editIssue;

import java.io.IOException;


import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issueTracker.abstractController.AbstractBaseController;
import org.training.issueTracker.beans.Build_Found;
import org.training.issueTracker.beans.Project;
import org.training.issueTracker.beans.Task;
import org.training.issueTracker.beans.User;
import org.training.issueTracker.constants.ConstantsController;
import org.training.issueTracker.exception.DaoException;
import org.training.issueTracker.modelTask.ITaskDAO;
import org.training.issueTracker.modelTask.TaskFactory;



/**
 * Servlet implementation class EditIssueController
 */
public class EditIssueController extends AbstractBaseController {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public EditIssueController() {
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String path=getServletContext().getRealPath("/")+ConstantsController.SERVLET_PATH;
		 int idIssue=EditIssueValidator.getIdIssue(request);
		 ITaskDAO task= TaskFactory.getClassFromFactory(path);
		 	List<String> status=null;
			List<String> types=null;
			List<String> priority=null;
			List<Project> project=null;
			List<Build_Found> builds=null;
			List<User>  users=null;
			List<String> resolutions=null;
			Task issue=null;
		 try {
			 issue=task.getTaskForId(idIssue);
			 status=task.getAllStatus();
		
			 types = task.getAllType();
			 priority=task.getAllPriority();
			 project=task.getAllProgect();
			 builds=task.getAllBuild();
			 users=task.getAllUser();
			 resolutions=task.getAllResolution();
			 request.setAttribute(ConstantsController.ATTRIBUTE_ID_ISSUE, idIssue);
			 request.setAttribute(ConstantsController.ATTRIBUTE_ISSUE, issue);
			 request.setAttribute(ConstantsController.ATTRIBUTE_ALL_STATUS, status);
			 request.setAttribute(ConstantsController.ATTRIBUTE_ALL_TYPE, types);
			 request.setAttribute(ConstantsController.ATTRIBUTE_ALL_PRIORITY, priority);
			 request.setAttribute(ConstantsController.ATTRIBUTE_ALL_PROJECT, project);
			 request.setAttribute(ConstantsController.ATTRIBUTE_ALL_BUILD, builds);
			 request.setAttribute(ConstantsController.ATTRIBUTE_ALL_USERS, users);
			 request.setAttribute(ConstantsController.ATTRIBUTE_ALL_RESOLUTIONS, resolutions);
			 jump(ConstantsController.JUMP_EDIT_PAGE, request, response);
	
		} catch (DaoException e) {
			
			jump(ConstantsController.JUMP_WELCOME_PAGE,e.getMessage() , request, response);
		}
		
	}

	protected void jump(String url, String ErrorMessage, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute(ConstantsController.ATTRIBUTE_PROBLEM_EDIT_ISSUE, ErrorMessage);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
