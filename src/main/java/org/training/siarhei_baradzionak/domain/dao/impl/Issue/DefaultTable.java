package org.training.siarhei_baradzionak.domain.dao.impl.Issue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.training.siarhei_baradzionak.domain.beans.issue.Issue;
import org.training.siarhei_baradzionak.domain.beans.issue.Priority;
import org.training.siarhei_baradzionak.domain.beans.issue.Resolution;
import org.training.siarhei_baradzionak.domain.beans.issue.Status;
import org.training.siarhei_baradzionak.domain.beans.issue.Type;
import org.training.siarhei_baradzionak.domain.beans.project.BuildProject;
import org.training.siarhei_baradzionak.domain.beans.project.Project;
import org.training.siarhei_baradzionak.domain.beans.users.RolesUser;
import org.training.siarhei_baradzionak.domain.beans.users.User;
import org.training.siarhei_baradzionak.domain.constants.ServletConstants;
import org.training.siarhei_baradzionak.domain.dao.impl.usersDAO.UserImp;
import org.training.siarhei_baradzionak.exceptions.ExceptionDAO;
@Repository
public class DefaultTable {
	private Map<Integer, String> issueTypes = new HashMap<Integer, String>();
	private Map<Integer, String> issueStatuses = new HashMap<Integer, String>();
	private Map<Integer, String> issueResolutions = new HashMap<Integer, String>();
	private Map<Integer, String> issuePriorities = new HashMap<Integer, String>();
	private List<String> rolesList = new ArrayList<String>();
	@Autowired
	private SessionFactory sessionFactory;
	public DefaultTable() {

		createDataRole();
		try {
			createDataUser();
		} catch (ExceptionDAO e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createData(Type.class.getSimpleName(), issueTypes);

		createData(Status.class.getSimpleName(), issueStatuses);

		createData(Resolution.class.getSimpleName(), issueResolutions);

		createData(Priority.class.getSimpleName(), issuePriorities);

	}

	private void createDataIssue() throws ExceptionDAO {
		
			
		

			// issue for user
			Issue issue = new Issue();
			issue.setSummary("It's the summary about issue");
			issue.setDescription("It's the issue description");
			User assignee = new UserImp()
					.getUserByEmail(ServletConstants.DEFAULT_USER_EMAIL_ADDRESS);
			issue.setAssignee(assignee);
			User createdUser = new UserImp()
					.getUserByEmail(ServletConstants.DEFAULT_USER_EMAIL_ADDRESS);
			issue.setCreatedby(createdUser);
			Calendar calendar = Calendar.getInstance();
			issue.setCreateDate((Date) calendar.getTime());

			// find and set data from exist data
			CommonDAO commonService = new CommonDAO();
			Status status = commonService.getStatusByName("Assigned");
			issue.setStatus(status);

			Type type = commonService.getTypeByName("Bug");
			issue.setType(type);

			Priority priority = commonService.getPriorityByName("Important");
			issue.setPriority(priority);

			Resolution resolution = commonService
					.getResolutionByName("Wontfix");
			issue.setResolution(resolution);

			Project project = commonService.getProjectByName("MyFirstProject");
			issue.setProject(project);

			status.getIssues().add(issue);
			type.getIssues().add(issue);
			priority.getIssues().add(issue);
			resolution.getIssues().add(issue);
			project.getIssues().add(issue);

			IssueService issueService = new IssueService();
			issueService.setIssue(issue);

			// issue for admin
			issue = new Issue();
			issue.setSummary("It's the issue for admin");
			issue.setDescription("It's the issue for admin description");
			assignee = new UserImp()
					.getUserByEmail(ServletConstants.DEFAULT_ADMIN_EMAIL_ADDRESS);
			issue.setAssignee(assignee);
			createdUser = new UserImp()
					.getUserByEmail(ServletConstants.DEFAULT_ADMIN_EMAIL_ADDRESS);
			issue.setCreatedby(createdUser);
			issue.setCreateDate((Date) calendar.getTime());

			// find and set data from exist data
			status = commonService.getStatusByName("Assigned");
			issue.setStatus(status);

			type = commonService.getTypeByName("Bug");
			issue.setType(type);

			priority = commonService.getPriorityByName("Important");
			issue.setPriority(priority);

			resolution = commonService.getResolutionByName("Wontfix");
			issue.setResolution(resolution);

			project = commonService.getProjectByName("MyFirstProject");
			issue.setProject(project);

			status.getIssues().add(issue);
			type.getIssues().add(issue);
			priority.getIssues().add(issue);
			resolution.getIssues().add(issue);
			project.getIssues().add(issue);

			issueService.setIssue(issue);

		
			
	
	}

	private void createDataRole() {
		

			
			
			for (RolesUser role : RolesUser.values()) {
				sessionFactory.getCurrentSession().save(role);
			}

			
			
	}	
				
		

	private void createDataProject() throws ExceptionDAO {
		
			

		
			BuildProject buildFound = new BuildProject();
			buildFound.setNameBuild("1.0.1");

			Project project = new Project();
			project.setNameProject("MyFirstProject");
			project.setDescriptionProject("It's my first project description");

			UserImp userService = new UserImp();
			User manager = userService
					.getUserByEmail(ServletConstants.DEFAULT_USER_EMAIL_ADDRESS);

			project.setManagerProject(manager);
			project.getBuilds().add(buildFound);

			CommonDAO commonService = new CommonDAO();
			commonService.setProject(project);

			// add project2
			buildFound = new BuildProject();
			buildFound.setNameBuild("1.0.2");

			project = new Project();
			project.setNameProject("ProjectForAdmin");
			project.setDescriptionProject("It's project for administrator");

			manager = userService
					.getUserByEmail(ServletConstants.DEFAULT_USER_EMAIL_ADDRESS);

			project.setManagerProject(manager);
			project.getBuilds().add(buildFound);

			commonService.setProject(project);

			

					
	}

	private void createDataUser() throws ExceptionDAO {
		
			User userDef = new User();
			userDef.setFirstName(ServletConstants.DEFAULT_ADMIN_FIRST_NAME);
			userDef.setLastName(ServletConstants.DEFAULT_ADMIN_LAST_NAME);
			userDef.setEmail(ServletConstants.DEFAULT_ADMIN_EMAIL_ADDRESS);
			userDef.setPassword(ServletConstants.DEFAULT_ADMIN_PASSWORD);
			
			userDef.setRole(ServletConstants.DEFAULT_ADMIN_ROLE);

			

			UserImp userService = new UserImp();
			userService.addNewUser(userDef);

			// add user
			userDef = new User();
			userDef.setFirstName(ServletConstants.DEFAULT_USER_FIRST_NAME);
			userDef.setLastName(ServletConstants.DEFAULT_USER_LAST_NAME);
			userDef.setEmail(ServletConstants.DEFAULT_USER_EMAIL_ADDRESS);
			userDef.setPassword(ServletConstants.DEFAULT_USER_PASSWORD);
			

			userService.addNewUser(userDef);

			
	}

	private void createData(String tableClassName, Map<Integer, String> values) {
		
			
			
			if (!values.isEmpty()) {
				
				Collection<String> instances = values.values();
				for (String instance : instances) {
					switch (tableClassName) {
					case "Type":
						Type type = new Type();
						type.setType(instance);
						sessionFactory.getCurrentSession().save(type);
						break;
					case "Status":
						Status status = new Status();
						status.setStatus(instance);
						sessionFactory.getCurrentSession().save(status);
						break;
					case "Resolution":
						Resolution resolution = new Resolution();
						resolution.setResolution(instance);
						sessionFactory.getCurrentSession().save(resolution);
						break;
					case "Priority":
						Priority priority = new Priority();
						priority.setPriority(instance);
						sessionFactory.getCurrentSession().save(priority);
						break;
					default:
						System.out.println("Class not found");
						break;
					}
				}
			
			}
			
	}

	private boolean ifExistData(String tableClassName) {
		
				List list = sessionFactory.getCurrentSession().createQuery("from " + tableClassName)
						.list();
				return true;
				
			
	}

	private boolean ifExistDataRole() {
		
			for (RolesUser role : RolesUser.values()) {
				rolesList.add(role.getUserRole());
			}

			if (rolesList != null) {
				
					for (String curRole : rolesList) {
						
						
						
					}
					
			

		
		return false;
			}
			return true;
	}
}
