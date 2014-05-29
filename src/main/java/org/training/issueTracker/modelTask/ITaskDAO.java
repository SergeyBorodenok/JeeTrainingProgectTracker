package org.training.issueTracker.modelTask;

import java.util.List;

import org.training.issueTracker.beans.Build_Found;
import org.training.issueTracker.beans.Project;
import org.training.issueTracker.beans.Task;
import org.training.issueTracker.beans.User;
import org.training.issueTracker.exception.DaoException;

public interface ITaskDAO {
	List<Task> getAllTask() throws DaoException;
	List<Task> getTaskUser(User user) throws DaoException;
	List<String> getAllStatus() throws DaoException;
	List<String> getAllType() throws DaoException; 
	List<String> getAllPriority() throws DaoException;
	List<Project> getAllProgect() throws DaoException;
	List<Build_Found> getAllBuild() throws DaoException;
	List<User> getAllUser() throws DaoException;
	List<String> getAllResolution() throws DaoException;
	void insertIssue(User user,String summary,String description,String status,String type,String priority,String project,String build,String userLoginAssignee) throws DaoException;
	Task getTaskForId(int id) throws DaoException;
	void updateIssue(int issue_id, User user,String summary,String description,String status,String type,String priority,String project,String build,String userLoginAssignee,String resolution) throws DaoException;
	List<Task> getAllTaskSorted(String colum, String kind) throws DaoException;
	List<Task> getSortedTaskUser(User user, String colum, String kind)  throws DaoException;
	
}
