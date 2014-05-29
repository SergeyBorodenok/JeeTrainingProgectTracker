package by.epamlab.authentification;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import by.epamlab.beans.User;
import by.epamlab.constants.Constant;
import by.epamlab.handler.UserHandler;
import by.epamlab.validator.ValidatorAuthentification;


/**
 * Servlet Filter implementation class AuthentificationFilter
 */
public class AuthentificationFilter implements Filter {
	
	private List<User> users;
    /**
     * Default constructor. 
     */
    public AuthentificationFilter() {
       
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String user=null;
		String userName=null;
		String password=null;
		
		
		userName=ValidatorAuthentification.getNameAttributeLogin((HttpServletRequest) request);
		password=ValidatorAuthentification.getNameAttributePassword((HttpServletRequest) request);
		if (userName!=null && password!=null ) {
			
			user=ValidatorAuthentification.getUserMenu(users, userName, password);
			
			
			if (user==null) {
				request.setAttribute(Constant.ATTRIBUTE_PROBLEM_AUTHENTIFICATION,Constant.PROBLEM_AUTHENTIFICATION);
				
			}else{
				request.setAttribute(Constant.ATTRIBUTE_USERS_MENU,user);
			}
			
			
		}
		
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
		SAXParserFactory factorySAX = SAXParserFactory.newInstance();
		UserHandler userHandler=new UserHandler();
		try {
			SAXParser parserSAX = factorySAX.newSAXParser();

			parserSAX.parse((fConfig.getServletContext().getRealPath("/")+Constant.WAR_PATH+Constant.NAME_XML_USERS_FILE), userHandler);
			

			

		} catch (ParserConfigurationException e) {
		
			System.out.println("ErrorConfiguration " + e);
		} catch (SAXException e) {
			
			System.out.println("Error SAXExseption " + e);
		} catch (IOException e) {
			
			System.out.println("Error IOExseption " + e);
		}
		users=userHandler.getUsers();
	}

}
