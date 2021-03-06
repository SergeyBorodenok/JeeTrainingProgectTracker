package org.training.issueTracker.guestLoading;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issueTracker.abstractController.AbstractBaseController;
import org.training.issueTracker.beans.Task;
import org.training.issueTracker.constants.ConstantsController;
import org.training.issueTracker.exception.DaoException;
import org.training.issueTracker.modelTask.ITaskDAO;
import org.training.issueTracker.modelTask.TaskFactory;

/**
 * Servlet implementation class GuestController
 */
public class GuestController extends AbstractBaseController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=getServletContext().getRealPath("/")+ConstantsController.SERVLET_PATH;
		ITaskDAO task= TaskFactory.getClassFromFactory(path);
		List<Task> allTask=null;
		
		try {
			allTask=task.getAllTask();
		} catch (DaoException e) {
			
			jump(ConstantsController.JUMP_WELCOME_PAGE,e.getMessage() , request, response);
			return;
		}
		
		request.getSession().setAttribute(ConstantsController.ATTRIBUTE_LIST_ALL_TASK, allTask);
		jump(ConstantsController.JUMP_WELCOME_PAGE, request, response);
		
	}

	protected void jump(String url, String ErrorMessage, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute(ConstantsController.ATTRIBUTE_PROBLEM_AUTHENTIFICATION, ErrorMessage);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
