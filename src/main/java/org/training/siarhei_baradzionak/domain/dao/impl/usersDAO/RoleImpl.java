package org.training.siarhei_baradzionak.domain.dao.impl.usersDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.training.siarhei_baradzionak.domain.beans.users.RolesUser;
import org.training.siarhei_baradzionak.domain.dao.interfaceDAO.IUsers.IRoleDAO;
import org.training.siarhei_baradzionak.exceptions.ExceptionDAO;
@Repository
public class RoleImpl implements IRoleDAO {
	@Autowired
    private SessionFactory sessionFactory;
	public RolesUser getExistRole(RolesUser role) throws ExceptionDAO {
		RolesUser userRole = null;
		
		
			userRole = (RolesUser) sessionFactory.getCurrentSession()
					.createQuery("from Role r where r.roleName = ?")
					.setString(0, role.toString()).uniqueResult();
		
		return userRole;
	}

	@Override
	public List<RolesUser> getExistRoles() throws ExceptionDAO {
		List<RolesUser> roles = new ArrayList<RolesUser>();
		
			roles = (List<RolesUser>) sessionFactory.getCurrentSession().createQuery("from Role").list();
		
		return roles;
	}
}
