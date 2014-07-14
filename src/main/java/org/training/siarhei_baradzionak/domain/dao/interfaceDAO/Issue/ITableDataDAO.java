package org.training.siarhei_baradzionak.domain.dao.interfaceDAO.Issue;

import java.util.List;

import org.training.siarhei_baradzionak.domain.beans.issue.Priority;
import org.training.siarhei_baradzionak.domain.beans.issue.Resolution;
import org.training.siarhei_baradzionak.domain.beans.issue.Status;
import org.training.siarhei_baradzionak.domain.beans.issue.Type;
import org.training.siarhei_baradzionak.domain.beans.project.BuildProject;
import org.training.siarhei_baradzionak.domain.beans.project.Project;
import org.training.siarhei_baradzionak.domain.beans.users.User;
import org.training.siarhei_baradzionak.exceptions.ExceptionDAO;

public interface ITableDataDAO {
	public List<Status> getStatuses() throws ExceptionDAO;
public List<Type> getTypes() throws ExceptionDAO;
public List<Resolution> getResolutions() throws ExceptionDAO;
public List<Priority> getPriorities() throws ExceptionDAO;
public List<Project> getProjects() throws ExceptionDAO;
public BuildProject getBuildProject(Project project, String build) throws ExceptionDAO;
public List<BuildProject> getBuild() throws ExceptionDAO;
public List<BuildProject> getBuildsByProjectId(int projectId) throws ExceptionDAO;
public List<User> getUsers() throws ExceptionDAO;

public boolean setStatus(Status status) throws ExceptionDAO;
public boolean setType(Type type) throws ExceptionDAO;
public boolean setResolution(Resolution resolution) throws ExceptionDAO;
public boolean setPriority(Priority priority) throws ExceptionDAO;
public boolean setProject(Project project) throws ExceptionDAO;
public boolean setBuild(Project project, String buildValue) throws ExceptionDAO;

public Status getStatusById(int statusId) throws ExceptionDAO;
public Type getTypeById(int typeId) throws ExceptionDAO;
public Resolution getResolutionById(int resolutionId) throws ExceptionDAO;
public Priority getPriorityById(int priorityId) throws ExceptionDAO;
public Project getProjectById(int projectsId) throws ExceptionDAO;

public boolean updateStatus(Status status) throws ExceptionDAO;
public boolean updateType(Type type) throws ExceptionDAO;
public boolean updateResolution(Resolution resolution) throws ExceptionDAO;
public boolean updatePriority(Priority priority) throws ExceptionDAO;
public boolean updateProject(Project project) throws ExceptionDAO;
}
