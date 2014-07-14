package org.training.siarhei_baradzionak.domain.dao.interfaceDAO.IUsers;

import java.util.List;

import org.training.siarhei_baradzionak.domain.beans.users.RolesUser;
import org.training.siarhei_baradzionak.exceptions.ExceptionDAO;

public interface IRoleDAO {
	public RolesUser getExistRole(RolesUser role) throws ExceptionDAO;
	public List<RolesUser> getExistRoles() throws ExceptionDAO;
}
