package org.training.issueTracker.abstractController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AbstractBaseController
 */
public abstract  class AbstractBaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 performTask(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 performTask(request, response);
	}
	
	/**
	 * Perform task.
	 * 
	 * @param request the request
	 * @param response the response
	 * 
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	abstract protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	   /**
     * Jump.
     * 
     * @param url the url
     * @param request the request
     * @param response the response
     * 
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
	 protected void jump(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		    rd.forward(request, response);
		  }

}
