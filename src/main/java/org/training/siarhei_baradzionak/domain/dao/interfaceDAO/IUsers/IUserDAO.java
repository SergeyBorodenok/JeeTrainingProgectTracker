package org.training.siarhei_baradzionak.domain.dao.interfaceDAO.IUsers;

import java.util.List;

import org.training.siarhei_baradzionak.domain.beans.users.User;
import org.training.siarhei_baradzionak.exceptions.ExceptionDAO;

public interface IUserDAO {
	public List<User> getExistUsers() throws ExceptionDAO;
	public User getExistUser(String emailAddress, String password) throws ExceptionDAO;
	public User getUserById(int id) throws ExceptionDAO;
	public boolean addNewUser(User user) throws ExceptionDAO;
	public boolean updateUser(User user) throws ExceptionDAO;
	public boolean updateUserPassword(User user) throws ExceptionDAO;
}
