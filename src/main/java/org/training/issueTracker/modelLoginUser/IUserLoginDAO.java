package org.training.issueTracker.modelLoginUser;

import org.training.issueTracker.beans.User;
import org.training.issueTracker.exception.DaoException;



public interface IUserLoginDAO {
	User getUser(String login, String password) throws DaoException ;
}
