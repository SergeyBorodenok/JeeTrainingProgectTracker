package by.epamlab.servlet;

import java.io.IOException;
import java.io.PrintWriter;




import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamlab.constants.Constant;
import by.epamlab.validator.ValidatorWelcomPage;



/**
 * Servlet implementation class WelcomPage
 */
public class WelcomPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user=ValidatorWelcomPage.getNameAttributeUser(request);	
		String problemAuthentification=ValidatorWelcomPage.getNameAttributeProblemWithAuthentification(request);	
		
		
			
			PrintWriter out=response.getWriter();
			out.print(Constant.BEGINING_HTML_PAGE);
			out.print(Constant.HTML_TITLE_WELCOM_PAGE);
			out.print(Constant.START_BODY_HTML_PAGE);
			if (user==null) {
				out.print(Constant.FORM_REGISTRATION);
						if (problemAuthentification!=null) {
							out.print(Constant.SIGNATURE_PROBLEM_AUTHENTIFICATION_BEGINING+problemAuthentification+Constant.SIGNATURE_PROBLEM_AUTHENTIFICATION_ENDING);
						}
						
				out.print(Constant.SEARCH_BUTTON);
				
			}else{
				out.print(user+Constant.SEARCH_BUTTON);
			}
			out.print(Constant.INFORMATION_ABOUT_PROBLEM__IN_PROGECT);
			out.print(Constant.ENDING_HTML_PAGE);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
