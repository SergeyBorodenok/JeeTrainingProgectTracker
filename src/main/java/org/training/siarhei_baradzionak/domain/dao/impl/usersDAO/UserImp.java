package org.training.siarhei_baradzionak.domain.dao.impl.usersDAO;

import java.util.List;






import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.training.siarhei_baradzionak.domain.beans.users.User;
import org.training.siarhei_baradzionak.domain.dao.interfaceDAO.IUsers.IUserDAO;
import org.training.siarhei_baradzionak.exceptions.ExceptionDAO;

@Repository
public class UserImp implements  IUserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getExistUsers() throws ExceptionDAO {
		
		return (List<User>) sessionFactory.getCurrentSession().createQuery("from User").list();
		
	}

	@Override
	public User getExistUser(String emailAddress, String password)
			throws ExceptionDAO {
		// TODO Auto-generated method stub
		return 	 (User) sessionFactory.getCurrentSession().createQuery(
				"from User u where u.emailAddress = ? and u.password = ?")
				.setString(0, emailAddress).setString(1, password)
				.uniqueResult();
	}

	@Override
	public User getUserById(int id) throws ExceptionDAO {
		
		return  (User) sessionFactory.getCurrentSession().createQuery(
				"from User u where u.userId = ?")
				.setInteger(0, id).uniqueResult();
	}

	@Override
	public boolean addNewUser(User user) throws ExceptionDAO {


		sessionFactory.getCurrentSession().save(user);
		return true;
	}

	@Override
	public boolean updateUser(User user) throws ExceptionDAO {
		sessionFactory.getCurrentSession().update(user);
		return true;
		
	}

	@Override
	public boolean updateUserPassword(User user) throws ExceptionDAO {
		sessionFactory.getCurrentSession().update(user);
		return true;
		
	}

	public User getUserByEmail(String defaultUserEmailAddress) {
		return 	 (User) sessionFactory.getCurrentSession().createQuery(
				"from User u where u.emailAddress = ? ")
				.setString(0, defaultUserEmailAddress)
				.uniqueResult();
	}
	
	
	

	

}
