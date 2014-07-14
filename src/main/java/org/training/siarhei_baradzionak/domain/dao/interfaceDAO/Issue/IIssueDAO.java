package org.training.siarhei_baradzionak.domain.dao.interfaceDAO.Issue;

import java.util.List;

import org.training.siarhei_baradzionak.domain.beans.issue.Issue;
import org.training.siarhei_baradzionak.exceptions.ExceptionDAO;

public interface IIssueDAO {
	public List<Issue> getAllIssues() throws ExceptionDAO;

	public List<Issue> getUserIssues(int userId) throws ExceptionDAO;

	public List<Issue> getIssues(int firstNumber, int number)
			throws ExceptionDAO;

	public Issue getIssueById(int issueId) throws ExceptionDAO;

	public boolean setIssue(Issue issue) throws ExceptionDAO;

	public boolean updateIssue(Issue issue) throws ExceptionDAO;
}
