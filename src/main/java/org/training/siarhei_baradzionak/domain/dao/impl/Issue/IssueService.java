package org.training.siarhei_baradzionak.domain.dao.impl.Issue;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.training.siarhei_baradzionak.domain.beans.issue.Issue;
import org.training.siarhei_baradzionak.domain.dao.interfaceDAO.Issue.IIssueDAO;
import org.training.siarhei_baradzionak.exceptions.ExceptionDAO;
@Repository
public class IssueService implements IIssueDAO {
	
	@Autowired
    private SessionFactory sessionFactory;
	@Override
	public List<Issue> getAllIssues() throws ExceptionDAO {
		
		List<Issue> issues = new ArrayList<Issue>();
		
			issues = (List<Issue>) sessionFactory.getCurrentSession().createQuery("from Issue").list();
		
		
		return issues;
	}

	@Override
	public List<Issue> getUserIssues(int userId) throws ExceptionDAO {
		
		List<Issue> list = null;
		
			list = (List<Issue>) sessionFactory.getCurrentSession()
					.createQuery("from Issue i where i.assignee.userId = ?")
					.setInteger(0, userId).list();
		
		return list;
	}

	
	@Override
	public Issue getIssueById(int issueId) throws ExceptionDAO {
		
		Issue issue = null;
		
			issue = (Issue) sessionFactory.getCurrentSession().createQuery("from Issue i WHERE i.id = ?")
					.setInteger(0, issueId).uniqueResult();
			
		
		return issue;
	}

	@Override
	public boolean setIssue(Issue issue) throws ExceptionDAO {
		boolean isSet = false;
		
		sessionFactory.getCurrentSession().save(issue);
			
			isSet = true;
		
		return isSet;
	}

	@Override
	public boolean updateIssue(Issue issue) throws ExceptionDAO {
		boolean isUpdate = false;
		
		sessionFactory.getCurrentSession().update(issue);
			
			isUpdate = true;
		
		return isUpdate;
	}

	@Override
	public List<Issue> getIssues(int firstNumber, int number)
			throws ExceptionDAO {
		List<Issue> list = null;
		list = (List<Issue>) sessionFactory.getCurrentSession()
				.createQuery("from Issue i where i.ID > ? and i.ID<?")
				.setInteger(0, firstNumber).setInteger(1, number).list();
		return list;
	}

	

	

}
