package org.training.siarhei_baradzionak.domain.dao.impl.Issue;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.training.siarhei_baradzionak.domain.beans.issue.Priority;
import org.training.siarhei_baradzionak.domain.beans.issue.Resolution;
import org.training.siarhei_baradzionak.domain.beans.issue.Status;
import org.training.siarhei_baradzionak.domain.beans.issue.Type;
import org.training.siarhei_baradzionak.domain.beans.project.BuildProject;
import org.training.siarhei_baradzionak.domain.beans.project.Project;
import org.training.siarhei_baradzionak.domain.beans.users.User;
import org.training.siarhei_baradzionak.domain.dao.interfaceDAO.Issue.ITableDataDAO;
import org.training.siarhei_baradzionak.exceptions.ExceptionDAO;
@Repository
public class CommonDAO implements ITableDataDAO {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public boolean setStatus(Status status) {
		boolean isSet = false;
	
		
		sessionFactory.getCurrentSession().save(status);
			
			isSet = true;
		
		return isSet;
	}

	@Override
	public boolean setType(Type type) throws ExceptionDAO {
		boolean isSet = false;
		
		sessionFactory.getCurrentSession().save(type);
			
			isSet = true;
		
		return isSet;
	}

	@Override
	public boolean setResolution(Resolution resolution) throws ExceptionDAO {
		boolean isSet = false;
		
		
			sessionFactory.getCurrentSession().save(resolution);
			
			isSet = true;
		
		return isSet;
	}

	@Override
	public boolean setPriority(Priority priority) throws ExceptionDAO {
		boolean isSet = false;
		
		sessionFactory.getCurrentSession().save(priority);
			
			isSet = true;
		
		return isSet;
	}

	@Override
	public boolean setProject(Project project) throws ExceptionDAO {
		boolean isSet = false;
		
			
			sessionFactory.getCurrentSession().save(project);
			
			isSet = true;
		
		return isSet;
	}

	/*public boolean isExist(DefaultTableClass arg) {
		boolean isSet = false;
		
		try {
			session.beginTransaction();
			isSet = session.contains(arg);
			session.getTransaction().commit();
		
		closeSession(session);
		return isSet;
	}

	public DefaultTableClass getRowFromTableById(DefaultTableClass arg, int id) {
		System.out.println("Get data by id from " + arg);
		LOG.info("Get data by id from " + arg);
		Session session = openSession();
		try {
			session.beginTransaction();
			arg = (DefaultTableClass) session
					.createQuery(
							"from " + arg.getClass().getSimpleName()
									+ " a where a.id = ?").setInteger(0, id)
					.uniqueResult();
			if (arg == null) {
				session.getTransaction().commit();
				closeSession(session);
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getHibSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in Get data from " + arg + " " + e);
		}
		closeSession(session);
		return arg;
	}
*/
	
	public Status getStatusByName(String name) {
		
		Status status = new Status();
		
			status = (Status) sessionFactory.getCurrentSession()
					.createQuery("from Status s where s.statusName = ?")
					.setString(0, name).uniqueResult();
			
		return status;
	}

	@Override
	public Status getStatusById(int statusId) {
		
		Status status = new Status();
		
			status = (Status) sessionFactory.getCurrentSession()
					.createQuery("from Status s where s.id = ?")
					.setInteger(0, statusId).uniqueResult();
			
		return status;
	}

	public Type getTypeByName(String name) {
		
		Type type = new Type();
		
			type = (Type)  sessionFactory.getCurrentSession()
					.createQuery("from Type t where t.typeName = ?")
					.setString(0, name).uniqueResult();
			
		return type;
	}

	@Override
	public Type getTypeById(int typeId) {
		
		
		Type type = new Type();
		
			type = (Type)  sessionFactory.getCurrentSession().createQuery("from Type t where t.id = ?")
					.setInteger(0, typeId).uniqueResult();
			
		
		
		return type;
	}

	public Priority getPriorityByName(String name) {
		
		Priority priority = new Priority();
	
			priority = (Priority) sessionFactory.getCurrentSession()
					.createQuery("from Priority p where p.priorityName = ?")
					.setString(0, name).uniqueResult();
			
		return priority;
	}

	@Override
	public Priority getPriorityById(int priorityId) {
		
		Priority priority = new Priority();
		
			priority = (Priority) sessionFactory.getCurrentSession()
					.createQuery("from Priority p where p.id = ?")
					.setInteger(0, priorityId).uniqueResult();
			
	
		return priority;
	}

	public Resolution getResolutionByName(String name) {
		
		Resolution resolution = new Resolution();
		
			resolution = (Resolution) sessionFactory.getCurrentSession()
					.createQuery("from Resolution r where r.resolutionName = ?")
					.setString(0, name).uniqueResult();
		
		return resolution;
	}

	@Override
	public Resolution getResolutionById(int resolutionId) {
		
		Resolution resolution = new Resolution();
		
			resolution = (Resolution) sessionFactory.getCurrentSession()
					.createQuery("from Resolution r where r.id = ?")
					.setInteger(0, resolutionId).uniqueResult();
			
		return resolution;
	}

	public Project getProjectByName(String name) {
		
		Project project = new Project();
		
			project = (Project) sessionFactory.getCurrentSession()
					.createQuery("from Project p where p.projectName = ?")
					.setString(0, name).uniqueResult();
			
		return project;
	}

	@Override
	public Project getProjectById(int projectId) {
		
		Project project = new Project();
		
			project = (Project) sessionFactory.getCurrentSession()
					.createQuery("from Project r where r.id = ?")
					.setInteger(0, projectId).uniqueResult();
			
		return project;
	}

	public Project getProjectByNameAndBuild(String name, String buildValue) {
		
		Project project = new Project();
		
		
			String sql = "SELECT p FROM Project p WHERE p.projectName = ?";
			project = (Project) sessionFactory.getCurrentSession().createQuery(sql).setString(0, name)
					.uniqueResult();
		
		return project;
	}

	@Override
	public List<Status> getStatuses() throws ExceptionDAO {
		
		List<Status> statuses = new ArrayList<Status>();
		
		
			statuses = (List<Status>) sessionFactory.getCurrentSession().createQuery("from Status").list();
			
		return statuses;
	}

	@Override
	public List<Type> getTypes() throws ExceptionDAO {
		
		List<Type> types = new ArrayList<Type>();
	
			types = (List<Type>) sessionFactory.getCurrentSession().createQuery("from Type").list();
			
		return types;
	}

	@Override
	public List<Resolution> getResolutions() throws ExceptionDAO {
		
		List<Resolution> resolutions = new ArrayList<Resolution>();
		
			resolutions = (List<Resolution>) sessionFactory.getCurrentSession().createQuery(
					"from Resolution").list();
			
		return resolutions;
	}

	@Override
	public List<Priority> getPriorities() throws ExceptionDAO {
		
		List<Priority> priorities = new ArrayList<Priority>();
	
			priorities = (List<Priority>) sessionFactory.getCurrentSession().createQuery("from Priority")
					.list();
			
		return priorities;
	}

	@Override
	public List<Project> getProjects() throws ExceptionDAO {
		
		List<Project> projects = new ArrayList<Project>();
		
			projects = (List<Project>) sessionFactory.getCurrentSession().createQuery("from Project")
					.list();
			
		return projects;
	}

	@Override
	public List<BuildProject> getBuild() throws ExceptionDAO {
		
		List<BuildProject> BuildProjects = new ArrayList<BuildProject>();
		
			BuildProjects = (List<BuildProject>) sessionFactory.getCurrentSession().createQuery(
					"from BuildProject").list();
			
		return BuildProjects;
	}

	@Override
	public List<User> getUsers() throws ExceptionDAO {
		
		List<User> users = new ArrayList<User>();
		
			users = (List<User>) sessionFactory.getCurrentSession().createQuery("from User").list();
			
		return users;
	}

	@Override
	public BuildProject getBuildProject(Project project, String build)
			throws ExceptionDAO {
		
		BuildProject BuildProject = new BuildProject();
		
			BuildProject = (BuildProject) sessionFactory.getCurrentSession().createQuery("from BuildProject b")
					.list();
		
		return BuildProject;

	}

	@Override
	public boolean updateStatus(Status status) throws ExceptionDAO {
		boolean isUpdate = false;
		
		sessionFactory.getCurrentSession().update(status);
			
			isUpdate = true;
		
		return isUpdate;
	}

	@Override
	public boolean updateType(Type type) throws ExceptionDAO {
		 sessionFactory.getCurrentSession().update(type);
		 return true;
	}

	@Override
	public boolean updateResolution(Resolution resolution) throws ExceptionDAO {
		sessionFactory.getCurrentSession().update(resolution);
		return true;
	}

	@Override
	public boolean updatePriority(Priority priority) throws ExceptionDAO {
		sessionFactory.getCurrentSession().update(priority);
		return true;
	}

	@Override
	public boolean updateProject(Project project) throws ExceptionDAO {
		sessionFactory.getCurrentSession().update(project);
		return true;
	}

	/*private boolean update(DefaultTableClass temp) throws ExceptionDAO {
		boolean isUpdate = false;
		System.out.println("Update in " + temp.getClass().getSimpleName());
		LOG.info("Update in resolution" + temp.getClass().getSimpleName());
		Session session = openSession();
		try {
			session.beginTransaction();
			session.update(temp);
			session.getTransaction().commit();
			isUpdate = true;
		} catch (Exception e) {
			HibernateUtil.getHibSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in Update "
					+ temp.getClass().getSimpleName() + " " + e);
		}
		closeSession(session);
		return isUpdate;
	}
	*/
	@Override
	public List<BuildProject> getBuildsByProjectId(int projectId)
			throws ExceptionDAO {
		
		List<BuildProject> BuildProjects = new ArrayList<BuildProject>();
		
			String sql = "SELECT p.builds FROM Project p WHERE p.id = ?";
			BuildProjects = (List<BuildProject>) sessionFactory.getCurrentSession().createQuery(sql)
					.setInteger(0, projectId).list();
			
		return BuildProjects;
	}

	@Override
	public boolean setBuild(Project project, String buildValue)
			throws ExceptionDAO {
		
		BuildProject BuildProject = new BuildProject();
		BuildProject.setNameBuild(buildValue);
		project.getBuilds().add(BuildProject);
		boolean isSet = false;
	
		sessionFactory.getCurrentSession().update(project);
			
			isSet = true;
		
		return isSet;
	}

	

}
