package org.training.issueTracker.authentification;

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
import org.training.issueTracker.modelLoginUser.IUserLoginDAO;
import org.training.issueTracker.modelLoginUser.UserLoginFactory;









/**
 * Servlet implementation class AuthentificationServlet
 */
public class AuthentificationServlet extends AbstractBaseController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthentificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=null;
		String password=null;
		String path=getServletContext().getRealPath("/")+ConstantsController.SERVLET_PATH;
		userName=ValidatorAuthentification.getNameAttributeLogin(request);
		password=ValidatorAuthentification.getNameAttributePassword(request);
		if (userName!=null && password!=null ) {
				IUserLoginDAO userDAO=UserLoginFactory.getClassFromFactory(path);	
				User user=null;
				
				try {
					user=userDAO.getUser(userName, password);
				} catch (DaoException e) {
					jump(ConstantsController.JUMP_WELCOME_PAGE,e.getMessage() , request, response);
					return;
				}
				if (user==null) {
					
					try {
						throw new DaoException(ConstantsController.ERROR_MESSAGE_PROBLEM_AUTHENTIFICATION);
					} catch (DaoException e) {
						jump(ConstantsController.JUMP_WELCOME_PAGE,ConstantsController.ERROR_MESSAGE_PROBLEM_AUTHENTIFICATION , request, response);
						return;
					}
					
				}else{
					request.getSession().setAttribute(ConstantsController.ATTRIBUTE_USER,user);
					jump(ConstantsController.JUMP_USER_TASK_CONTROLLER,request,response);
				  
				}
				
		}
		
	}
	protected void jump(String url, String ErrorMessage, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute(ConstantsController.ATTRIBUTE_PROBLEM_AUTHENTIFICATION, ErrorMessage);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
