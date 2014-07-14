package org.training.siarhei_baradzionak.domain.dao.impl.Issue;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.training.siarhei_baradzionak.domain.beans.issue.Comment;
import org.training.siarhei_baradzionak.domain.dao.interfaceDAO.Issue.ICommentDAO;
import org.training.siarhei_baradzionak.exceptions.ExceptionDAO;

@Repository
public class CommentService implements ICommentDAO {

	 	@Autowired
	    private SessionFactory sessionFactory;

	

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment>  getExistCommentsByIssueId(int issueId)
			throws ExceptionDAO {
		
		List<Comment> comments = new ArrayList<Comment>();
		
			comments = (List<Comment>) sessionFactory.getCurrentSession()
					.createQuery("from Comment c where c.issue.id = ?")
					.setInteger(0, issueId).list();
			
		
		return comments;
	}

	@Override
	public boolean addComment(Comment comment) throws ExceptionDAO {
		boolean isSet = false;
		
			
		sessionFactory.getCurrentSession().save(comment);
			
			isSet = true;
		
		return isSet;
	}

	

}
