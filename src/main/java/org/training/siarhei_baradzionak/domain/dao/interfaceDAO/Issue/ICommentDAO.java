package org.training.siarhei_baradzionak.domain.dao.interfaceDAO.Issue;

import java.util.List;

import org.training.siarhei_baradzionak.domain.beans.issue.Comment;
import org.training.siarhei_baradzionak.exceptions.ExceptionDAO;

public interface ICommentDAO {
	public List<Comment> getExistCommentsByIssueId(int issueId) throws ExceptionDAO;
	public boolean addComment(Comment comment) throws ExceptionDAO;
}
