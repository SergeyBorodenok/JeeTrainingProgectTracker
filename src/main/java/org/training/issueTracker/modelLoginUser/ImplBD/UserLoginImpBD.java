package org.training.issueTracker.modelLoginUser.ImplBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.training.issueTracker.beans.User;
import org.training.issueTracker.constants.ConstantsSQL;
import org.training.issueTracker.exception.DaoException;
import org.training.issueTracker.modelLoginUser.IUserLoginDAO;



public class UserLoginImpBD  implements IUserLoginDAO{
	
	private String path;
	public UserLoginImpBD(String path) {
		this.path=path;
	}

	@Override
	public User getUser(String login, String password) throws DaoException {
		 Connection conn=null;
	        PreparedStatement prStat=null;
	        ResultSet rs=null;
	      
	    
	        try {
				Class.forName(ConstantsSQL.DRIVER);
			} catch (ClassNotFoundException e) {
				
				throw new DaoException(ConstantsSQL.ERROR_DB);
			}
	        try {
	        	
	        	conn=DriverManager.getConnection(ConstantsSQL.PATH_H2_DB+path+ConstantsSQL.NAME_BD,ConstantsSQL.LOGIN_DB,ConstantsSQL.PASSWORD_DB);
	        	prStat=conn.prepareStatement(ConstantsSQL.QUERY_SELECT_USER);
	        	prStat.setString(1, login);
	        	prStat.setString(2, password);
	        	rs=prStat.executeQuery();
	        	if (rs.next()) {
					return new User(rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(2),rs.getString(3),rs.getInt(1));
					
				}
	        	
			} catch (SQLException  e) {
				throw new DaoException(ConstantsSQL.ERROR_DB);
			}finally{
				closeConnection(conn,prStat,rs);
			}
		return null;
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
