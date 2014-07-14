package org.training.siarhei_baradzionak.domain.controllers.showtabs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public  abstract class  AbstractBaseController extends javax.servlet.http.HttpServlet {
	 public AbstractBaseController() {
	        super();
	    }

	/**
	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
	performTask(request, response);
	}

	/**
	* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
	performTask(request, response);
	}

	abstract protected void performTask(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException;

	protected void jump(String url, HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
	RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
	rd.forward(request, response);
	}
}
