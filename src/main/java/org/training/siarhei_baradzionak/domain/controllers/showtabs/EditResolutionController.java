package org.training.siarhei_baradzionak.domain.controllers.showtabs;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.siarhei_baradzionak.domain.beans.issue.Resolution;
import org.training.siarhei_baradzionak.domain.constants.ServletConstants;
import org.training.siarhei_baradzionak.domain.dao.impl.Issue.CommonDAO;
import org.training.siarhei_baradzionak.domain.dao.interfaceDAO.Issue.ITableDataDAO;
import org.training.siarhei_baradzionak.exceptions.ExceptionDAO;


/**
 * Servlet implementation class EditResolutionController
 */

public class EditResolutionController extends AbstractBaseController {
	
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
		
		String resolutionName = request.getParameter(ServletConstants.JSP_EDIT_RESOLUTION);

		String inputResult = getInputResult(resolutionName);
		if(inputResult != null) {
			jump(ServletConstants.JUMP_EDIT_RESOLUTION_PAGE, inputResult, request, response);
			return;
		}
		
		try {
			//update resolution in db
			ITableDataDAO tableDataDAO = new CommonDAO();
			Resolution resolution = (Resolution) session.getAttribute(ServletConstants.JSP_EDIT_RESOLUTION);
			resolution.setResolution(resolutionName);
			boolean isUpdated = tableDataDAO.updateResolution(resolution);
			session.removeAttribute(ServletConstants.JSP_EDIT_RESOLUTION);
			if (isUpdated == true) {
				jumpError(ServletConstants.RESOLUTION_UPDATE_SUCCESSFULLY, request, response);
			} else {
				//  resolution not update
				jumpError(ServletConstants.ERROR_RESOLUTION_NOT_UPDATE, request, response);
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
		jump(ServletConstants.JUMP_MAIN_PAGE, message, request, response);
	}

	private String getInputResult(String resolutionStr) {
		if(resolutionStr == null || resolutionStr.equals("")) {
			return ServletConstants.ERROR_RESOLUTION_NAME_EMPTY;
		}
		return null;
	}

}
