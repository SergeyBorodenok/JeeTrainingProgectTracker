package org.training.issueTracker.modelTask.ImpBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.training.issueTracker.beans.Build_Found;
import org.training.issueTracker.beans.Project;
import org.training.issueTracker.beans.Task;
import org.training.issueTracker.beans.User;
import org.training.issueTracker.constants.ConstantsSQL;
import org.training.issueTracker.exception.DaoException;
import org.training.issueTracker.modelTask.ITaskDAO;

public class TaskImpBD implements ITaskDAO {
	
	private String path;
	public TaskImpBD(String path) {
		this.path=path;
	}

	@Override
	public List<Task> getAllTask() throws DaoException {
		List<Task> tasks=new ArrayList<Task>();
		
		Connection conn=null;
		Statement stat=null;
        
        ResultSet rs=null;
      
        try {
			Class.forName(ConstantsSQL.DRIVER);
		} catch (ClassNotFoundException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}
        try {
			conn=DriverManager.getConnection(ConstantsSQL.PATH_H2_DB+path+ConstantsSQL.NAME_BD,ConstantsSQL.LOGIN_DB,ConstantsSQL.PASSWORD_DB);
			stat=conn.createStatement();
			rs=stat.executeQuery(ConstantsSQL.QUERY_SELECT_ISSUE);
			int count=1;
			while (rs.next() && count<10) {
				int curId=rs.getInt(1);
				
				tasks.add(new Task(curId, rs.getDate(2), rs.getDate(3), rs.getString(6), findModifyUser(conn, curId), rs.getString(4),rs.getString(5), rs.getString(7), findResolution(conn, curId), rs.getString(8),
						rs.getString(9), rs.getString(10),rs.getString(11), findAssignee(conn, curId)));
				count++;
				
			}
			return tasks;
		} catch (SQLException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}finally{
			closeConnection(conn, stat, rs);
		}
		
	}
	
	@Override
	public List<Task> getTaskUser(User user) throws DaoException {
		List<Task> tasks=new ArrayList<Task>();
		
		Connection conn=null;
		PreparedStatement stat=null;
        
        ResultSet rs=null;
      
        try {
			Class.forName(ConstantsSQL.DRIVER);
		} catch (ClassNotFoundException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}
        try {
			conn=DriverManager.getConnection(ConstantsSQL.PATH_H2_DB+path+ConstantsSQL.NAME_BD,ConstantsSQL.LOGIN_DB,ConstantsSQL.PASSWORD_DB);
			stat=conn.prepareStatement(ConstantsSQL.QUERY_TASK_FOR_USER);
			stat.setInt(1, user.getId());
			rs=stat.executeQuery();
			int count=1;
			while (rs.next() && count<10) {
				int curId=rs.getInt(1);
				
				tasks.add(new Task(curId, rs.getDate(2), rs.getDate(3), rs.getString(6), findModifyUser(conn, curId), rs.getString(4),rs.getString(5), rs.getString(7), findResolution(conn, curId), rs.getString(8),
						rs.getString(9), rs.getString(10),rs.getString(11), findAssignee(conn, curId)));
				count++;
				
			}
			return tasks;
		} catch (SQLException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}finally{
			closeConnection(conn, stat, rs);
		}
		
	}

	@Override
	public List<String> getAllStatus() throws DaoException {
	
		List<String> status=new ArrayList<String>();
		
		
		Connection conn=null;
		Statement stat=null;
        
        ResultSet rs=null;
      
        try {
			Class.forName(ConstantsSQL.DRIVER);
		} catch (ClassNotFoundException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}
        try {
			conn=DriverManager.getConnection(ConstantsSQL.PATH_H2_DB+path+ConstantsSQL.NAME_BD,ConstantsSQL.LOGIN_DB,ConstantsSQL.PASSWORD_DB);
			stat=conn.createStatement();
			rs=stat.executeQuery(ConstantsSQL.QUERY_ALL_STATUS);
			
			while (rs.next()) {
				
				
				status.add(rs.getString(1));
				
				
			}
			return status;
		} catch (SQLException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}finally{
			closeConnection(conn, stat, rs);
		}
		
	}
	@Override
	public List<String> getAllType() throws DaoException {
		List<String> status=new ArrayList<String>();
		
		Connection conn=null;
		Statement stat=null;
        
        ResultSet rs=null;
      
        try {
			Class.forName(ConstantsSQL.DRIVER);
		} catch (ClassNotFoundException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}
        try {
			conn=DriverManager.getConnection(ConstantsSQL.PATH_H2_DB+path+ConstantsSQL.NAME_BD,ConstantsSQL.LOGIN_DB,ConstantsSQL.PASSWORD_DB);
			stat=conn.createStatement();
			rs=stat.executeQuery(ConstantsSQL.QUERY_ALL_TYPE);
			
			while (rs.next()) {
				
				
				status.add(rs.getString(1));
				
				
			}
			return status;
		} catch (SQLException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}finally{
			closeConnection(conn, stat, rs);
		}
		
	}
	@Override
	public List<String> getAllPriority() throws DaoException {
		
		List<String> status=new ArrayList<String>();
		
		Connection conn=null;
		Statement stat=null;
        
        ResultSet rs=null;
      
        try {
			Class.forName(ConstantsSQL.DRIVER);
		} catch (ClassNotFoundException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}
        try {
			conn=DriverManager.getConnection(ConstantsSQL.PATH_H2_DB+path+ConstantsSQL.NAME_BD,ConstantsSQL.LOGIN_DB,ConstantsSQL.PASSWORD_DB);
			stat=conn.createStatement();
			rs=stat.executeQuery(ConstantsSQL.QUERY_ALL_PRIORITY);
			
			while (rs.next()) {
				
				
				status.add(rs.getString(1));
				
				
			}
			return status;
		} catch (SQLException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}finally{
			closeConnection(conn, stat, rs);
		}
		
	}
	
	public List<Project> getAllProgect() throws DaoException {
		List<Project> status=new ArrayList<Project>();
		
		
		Connection conn=null;
		Statement stat=null;
        
        ResultSet rs=null;
      
        try {
			Class.forName(ConstantsSQL.DRIVER);
		} catch (ClassNotFoundException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}
        try {
			conn=DriverManager.getConnection(ConstantsSQL.PATH_H2_DB+path+ConstantsSQL.NAME_BD,ConstantsSQL.LOGIN_DB,ConstantsSQL.PASSWORD_DB);
			stat=conn.createStatement();
			rs=stat.executeQuery(ConstantsSQL.QUERY_ALL_PROJECT);
			
			while (rs.next()) {
				int project_id=rs.getInt(1);
				List <Build_Found> builds=getBuildForProject(project_id);
				
				status.add(new Project(rs.getInt(1), rs.getString(2), builds, rs.getString(3), 
						new User(rs.getString(10),rs.getString(11), rs.getString(12), rs.getString(8),rs.getString(9), rs.getInt(7)))) ;
				
				
			}
			return status;
		} catch (SQLException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}finally{
			closeConnection(conn, stat, rs);
		}
		
	}
	@Override
	public List<Build_Found> getAllBuild() throws DaoException {
		List<Build_Found> status=new ArrayList<Build_Found>();
		
		Connection conn=null;
		Statement stat=null;
        
        ResultSet rs=null;
      
        try {
			Class.forName(ConstantsSQL.DRIVER);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
        try {
			conn=DriverManager.getConnection(ConstantsSQL.PATH_H2_DB+path+ConstantsSQL.NAME_BD,ConstantsSQL.LOGIN_DB,ConstantsSQL.PASSWORD_DB);
			stat=conn.createStatement();
			rs=stat.executeQuery(ConstantsSQL.QUERY_ALL_BUILD);
			
			while (rs.next()) {
				
				
				status.add(new Build_Found(rs.getInt(1), rs.getString(2), rs.getInt(3)));
				
				
			}
			return status;
		} catch (SQLException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}finally{
			closeConnection(conn, stat, rs);
		}
		
	}
	@Override
	public List<User> getAllUser() throws DaoException {
		List<User> users=new ArrayList<User>();
		Connection conn=null;
        Statement prStat=null;
        ResultSet rs=null;
        
       
        try {
			Class.forName(ConstantsSQL.DRIVER);
		} catch (ClassNotFoundException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}
        try {
        	
        	conn=DriverManager.getConnection(ConstantsSQL.PATH_H2_DB+path+ConstantsSQL.NAME_BD,ConstantsSQL.LOGIN_DB,ConstantsSQL.PASSWORD_DB);
        	prStat=conn.createStatement();
        	;
        	rs=prStat.executeQuery(ConstantsSQL.QUERY_ALL_USER);
        	while (rs.next()) {
				users.add(new User(rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(2),rs.getString(3),rs.getInt(1)));
				
			}
        	return users;
		} catch (SQLException  e) {
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}finally{
			closeConnection(conn,prStat,rs);
		}
	
		
	}
	@Override
	public void insertIssue(User user, String summary, String description,
			String status, String type, String priority, String project,
			String build, String userLoginAssignee) throws DaoException {
		
		    
		    Connection conn=null;
			PreparedStatement stat=null;
	        
	       
	      
	        try {
				Class.forName(ConstantsSQL.DRIVER);
			} catch (ClassNotFoundException e) {
				
				throw new DaoException(ConstantsSQL.ERROR_DB);
			}
	        try {
				conn=DriverManager.getConnection(ConstantsSQL.PATH_H2_DB+path+ConstantsSQL.NAME_BD,ConstantsSQL.LOGIN_DB,ConstantsSQL.PASSWORD_DB);
				stat=conn.prepareStatement(ConstantsSQL.QUERY_INSERT_ISSUE);
				stat.setInt(1, user.getId());
				stat.setInt(2, user.getId());
				stat.setString(3, summary);
				stat.setString(4, description);
				stat.setString(5, status);
				stat.setString(6, type);
				stat.setString(7, priority);
				stat.setString(8, project);
				stat.setString(9, build);
				stat.setString(10, userLoginAssignee);
				stat.executeUpdate();
				
				
				
			} catch (SQLException e) {
				
				throw new DaoException(ConstantsSQL.ERROR_DB);
			}finally{
				closeConnection(conn, stat, null);
			}
					
		    
	}
	@Override
	public Task getTaskForId(int idIssue) throws DaoException {
		
		Connection conn=null;
		PreparedStatement stat=null;
        
        ResultSet rs=null;
      
        try {
			Class.forName(ConstantsSQL.DRIVER);
		} catch (ClassNotFoundException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}
        try {
			conn=DriverManager.getConnection(ConstantsSQL.PATH_H2_DB+path+ConstantsSQL.NAME_BD,ConstantsSQL.LOGIN_DB,ConstantsSQL.PASSWORD_DB);
			stat=conn.prepareStatement(ConstantsSQL.QUERY_TASK_FOR_ID);
			stat.setInt(1, idIssue);
			rs=stat.executeQuery();
			Task issue=null;
			if (rs.next() ) {
				int curId=rs.getInt(1);
				
			 issue=(new Task(curId, rs.getDate(2), rs.getDate(3), rs.getString(6), findModifyUser(conn, curId), rs.getString(4),rs.getString(5), rs.getString(7), findResolution(conn, curId), rs.getString(8),
						rs.getString(9), rs.getString(10),rs.getString(11), findAssignee(conn, curId)));
				
				
			}
			return issue;
		} catch (SQLException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}finally{
			closeConnection(conn, stat, rs);
		}
		
		
		
	}
	@Override
	public List<String> getAllResolution() throws DaoException {
		List<String> resolution=new ArrayList<String>();
		
		Connection conn=null;
		Statement stat=null;
        
        ResultSet rs=null;
      
        try {
			Class.forName(ConstantsSQL.DRIVER);
		} catch (ClassNotFoundException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}
        try {
			conn=DriverManager.getConnection(ConstantsSQL.PATH_H2_DB+path+ConstantsSQL.NAME_BD,ConstantsSQL.LOGIN_DB,ConstantsSQL.PASSWORD_DB);
			stat=conn.createStatement();
			rs=stat.executeQuery(ConstantsSQL.QUERY_ALL_RESOLUTIONS);
			
			while (rs.next()) {
				
				
				resolution.add(rs.getString(1));
				
				
			}
			return resolution;
		} catch (SQLException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}finally{
			closeConnection(conn, stat, rs);
		}
		
	}
	@Override
	public void updateIssue(int issue_id, User user, String summary, String description,
			String status, String type, String priority, String project,
			String build, String userLoginAssignee, String resolution)
			throws DaoException {
		
		
		
	 
		Connection conn=null;
		PreparedStatement stat=null;
        
       
      
        try {
			Class.forName(ConstantsSQL.DRIVER);
		} catch (ClassNotFoundException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}
        try {
			conn=DriverManager.getConnection(ConstantsSQL.PATH_H2_DB+path+ConstantsSQL.NAME_BD,ConstantsSQL.LOGIN_DB,ConstantsSQL.PASSWORD_DB);
			if (ConstantsSQL.STATUS_CLOSED.equals(status)) {
				stat=conn.prepareStatement(ConstantsSQL.QUERY_UPDATE_CLOSED_ISSUE);
				stat.setInt(1, user.getId());
				stat.setString(2, status);
				stat.setString(3, resolution);
				stat.setInt(4,issue_id);
			} else {
				stat=conn.prepareStatement(ConstantsSQL.QUERY_UPDATE_ISSUE);
				stat.setInt(1, user.getId());
				
				stat.setString(2, summary);
				stat.setString(3, description);
				stat.setString(4, status);
				stat.setString(5,resolution);
				stat.setString(6, type);
				stat.setString(7, priority);
				
				stat.setString(8, project);
				
				stat.setString(9, build);
				stat.setString(10, project);
				stat.setString(11, userLoginAssignee);
				stat.setInt(12,issue_id);

			}
			
			stat.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}finally{
			closeConnection(conn, stat, null);
		}
				
	}
	@Override
	public List<Task> getAllTaskSorted(String colum, String kind)
			throws DaoException {
		String sortQuery=null;
		if (ConstantsSQL.DIRECTION.equals(kind)) {
			 sortQuery=ConstantsSQL.QUERY_SELECT_ISSUE+ConstantsSQL.ORDER_BY+colum;
		}else{
			 sortQuery=ConstantsSQL.QUERY_SELECT_ISSUE+ConstantsSQL.ORDER_BY+colum+ConstantsSQL.DESC;
		}
		List<Task> tasks=new ArrayList<Task>();
		
		Connection conn=null;
		Statement stat=null;
        
        ResultSet rs=null;
      
        try {
			Class.forName(ConstantsSQL.DRIVER);
		} catch (ClassNotFoundException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}
        try {
			conn=DriverManager.getConnection(ConstantsSQL.PATH_H2_DB+path+ConstantsSQL.NAME_BD,ConstantsSQL.LOGIN_DB,ConstantsSQL.PASSWORD_DB);
			stat=conn.createStatement();
			rs=stat.executeQuery(sortQuery);
			int count=1;
			while (rs.next() && count<10) {
				int curId=rs.getInt(1);
				
				tasks.add(new Task(curId, rs.getDate(2), rs.getDate(3), rs.getString(6), findModifyUser(conn, curId), rs.getString(4),rs.getString(5), rs.getString(7), findResolution(conn, curId), rs.getString(8),
						rs.getString(9), rs.getString(10),rs.getString(11), findAssignee(conn, curId)));
				count++;
				
			}
			return tasks;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}finally{
			closeConnection(conn, stat, rs);
		}

		
	}
	@Override
	public List<Task> getSortedTaskUser(User user, String colum, String kind)
			throws DaoException {
		String sortQuery=null;
		if (ConstantsSQL.DIRECTION.equals(kind)) {
			 sortQuery=ConstantsSQL.QUERY_TASK_FOR_USER+ConstantsSQL.ORDER_BY+colum;
		}else{
			 sortQuery=ConstantsSQL.QUERY_TASK_FOR_USER+ConstantsSQL.ORDER_BY+colum+ConstantsSQL.DESC;
		}
		List<Task> tasks=new ArrayList<Task>();
		
		Connection conn=null;
		PreparedStatement stat=null;
        
        ResultSet rs=null;
      
        try {
			Class.forName(ConstantsSQL.DRIVER);
		} catch (ClassNotFoundException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}
        try {
			conn=DriverManager.getConnection(ConstantsSQL.PATH_H2_DB+path+ConstantsSQL.NAME_BD,ConstantsSQL.LOGIN_DB,ConstantsSQL.PASSWORD_DB);
			stat=conn.prepareStatement(sortQuery);
			stat.setInt(1, user.getId());
			rs=stat.executeQuery();
			int count=1;
			while (rs.next() && count<10) {
				int curId=rs.getInt(1);
				
				tasks.add(new Task(curId, rs.getDate(2), rs.getDate(3), rs.getString(6), findModifyUser(conn, curId), rs.getString(4),rs.getString(5), rs.getString(7), findResolution(conn, curId), rs.getString(8),
						rs.getString(9), rs.getString(10),rs.getString(11), findAssignee(conn, curId)));
				count++;
				
			}
			return tasks;
		} catch (SQLException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}finally{
			closeConnection(conn, stat, rs);
		}
		

	}

	
	private List <Build_Found> getBuildForProject (int project_id) throws DaoException{
		
		List<Build_Found> status=new ArrayList<Build_Found>();
		
		Connection conn=null;
		PreparedStatement stat=null;
        
        ResultSet rs=null;
      
        try {
			Class.forName(ConstantsSQL.DRIVER);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
        try {
			conn=DriverManager.getConnection(ConstantsSQL.PATH_H2_DB+path+ConstantsSQL.NAME_BD,ConstantsSQL.LOGIN_DB,ConstantsSQL.PASSWORD_DB);
			stat=conn.prepareStatement(ConstantsSQL.QUERY_GET_BUILD_FOR_PROJECT);
			stat.setInt(1, project_id);
			rs=stat.executeQuery();
			
			while (rs.next()) {
				
				
				status.add(new Build_Found(rs.getInt(1), rs.getString(2), rs.getInt(3)));
				
				
			}
			return status;
		} catch (SQLException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}finally{
			closeConnection(conn, stat, rs);
		}
		
		
	}
	private String findAssignee(Connection conn,int id) throws DaoException{
		PreparedStatement prStat=null;
		ResultSet rs=null;
		try {
			prStat=conn.prepareStatement(ConstantsSQL.QUERY_ASSIGNEE);
			prStat.setInt(1, id);
			rs=prStat.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}finally{
			closeConnection(null, prStat, rs);
		}
		return "";
	}
	private String findResolution(Connection conn,int id) throws DaoException{
		PreparedStatement prStat=null;
		ResultSet rs=null;
		try {
			prStat=conn.prepareStatement(ConstantsSQL.QUERY_RESOLUTION);
			prStat.setInt(1, id);
			rs=prStat.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}finally{
			closeConnection(null, prStat, rs);
		}
		return "";
	}
	private String findModifyUser(Connection conn,int id) throws DaoException{
		PreparedStatement prStat=null;
		ResultSet rs=null;
		try {
			prStat=conn.prepareStatement(ConstantsSQL.QUERY_MODIFY_USER);
			prStat.setInt(1, id);
			rs=prStat.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			
			throw new DaoException(ConstantsSQL.ERROR_DB);
		}finally{
			closeConnection(null, prStat, rs);
		}
		
		
		return "";
	}
	private void closeConnection(Connection connection, Statement statement, ResultSet resultSet) throws DaoException  {
		try{
		if (resultSet != null) {
		
				resultSet.close();
			
		}
		if (statement != null) {
			statement.close();
		}
		if (connection != null) {
			connection.close();
		}
		} catch (SQLException e) {
			throw new DaoException(ConstantsSQL.ERROR_DB);	
		}
	}

	
	
	
}
