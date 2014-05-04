package by.epamlab.constants;

public class Constant {
	public static final String NAME_XML_USERS_FILE = "Users.xml";
	public static final String PROBLEM_AUTHENTIFICATION = "Something wrong with Authentification. Please check your name and password";
	public static final String ATTRIBUTE_PROBLEM_AUTHENTIFICATION = "problemAuthentification";
	public static final String ATTRIBUTE_USERS_MENU = "user";
	public static final String WAR_PATH = "WEB-INF\\classes\\";
	public static final String WELCOM_PAGE = "WelcomPage";
	public static final String BEGINING_HTML_PAGE = "<HTML><HEAD>";
	public static final String HTML_TITLE_WELCOM_PAGE = "<TITLE>Welcom Page</TITLE>";
	public static final String START_BODY_HTML_PAGE = "</HEAD><BODY>";
	public static final String FORM_REGISTRATION = "<form name='form' action='WelcomPage'>"
													+ "<div> "+
													"<label> please registrate</label>"+"<br>"+
													"<label> please enter your name </label>"+
													"<input type='text' size='20' name='login'>"+
													"<br>"+
													"<label> please enter your password </label>"+
													"<input type='password' size='20' name='password'> "+"<br>"+
													"<INPUT TYPE='SUBMIT'  name='registrate' value='registrate'> "						
													+ " </div></form>";
	public static final String SIGNATURE_PROBLEM_AUTHENTIFICATION_BEGINING = "<br><p> <span style='color: red'>";
	public static final String SIGNATURE_PROBLEM_AUTHENTIFICATION_ENDING = "</span></p>";
	public static final String SEARCH_BUTTON = "&nbsp;&nbsp;<INPUT TYPE='SUBMIT'  name='search' value='search'> <br>";
	public static final String INFORMATION_ABOUT_PROBLEM__IN_PROGECT = "<span>we don't have problem in this progect</span>";
	public static final String ENDING_HTML_PAGE = "</body></HTML>";
	public static final String ROLE_USER = "USER";
	public static final String ROLE_ADMIN = "ADMIN";
	public static final String BEGINING_WELCOM_MESSAGE = "<label> Welcom dear &nbsp;&nbsp;&nbsp;&nbsp;";
	public static final String ENDING_WELCOM_MESSAGE = "</label>";
	public static final String USER_MENU = "<a href='#'>&nbsp; Edit your data</a>"+
											"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form name='form' action='Logout'><INPUT TYPE='SUBMIT'  "
											+ "  name='Logout' value='Logout' ></form>"+"<br>"
											+"<INPUT TYPE='SUBMIT'  name='Submit Issue' value='Submit Issue'>";
	
	public static final String ADMIN_MENU = "<a href='#'>&nbsp; Edit your data</a>"+
											"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form name='form' action='Logout'><INPUT TYPE='SUBMIT'    name='Logout' value='Logout' ></form>"+"<br>"
											+"<INPUT TYPE='SUBMIT'  name='Submit Issue' value='Submit Issue'>"
											+ "<br><br><br>"
											+ "<h1>SUBMENU ADMIN </h1>"
											+"<ul> <h2>Main menu</h2>"
											+"<style scoped>  li { display: inline; }  </style>"
											+ "<li><a href='#'>Projects</a></li> <li><a href='#'>Statuses</a></li> <li> <a href='#'>Resolutions</a></li>  "
											+ "<li><a href='#'>Priorities</a></li> <li><a href='#'>Types</a></li></ul>"
											+ "<ul><h2>Add menu</h2>"
											+"<style scoped>  li { display: inline; }  </style>"
											+ "<li><a href='#'>Project</a></li>  <a href='#'>Resolution</a></li>  "
											+ "<li><a href='#'>Priority</a></li> <li><a href='#'>Type</a></li>"
											+ " <li><a href='#'>Search User</a></li> <li><a href='#'>Add User</a></li></ul>";
	
	
}
