package org.training.siarhei_baradzionak.domain.beans.issue;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.training.siarhei_baradzionak.domain.beans.users.User;

@Entity
@Table(name="COMMENT")
public class Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="ID", unique = true, nullable = false)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="ADDED_BY",referencedColumnName = "ID")
	private User addedBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ADD_DATE")
	private Date addDate;
	
	@Column(name="COMMENT",nullable = false, length = 255)
	private String commet;
	
	
	@ManyToOne
	@JoinColumn(name = "ISSUE", referencedColumnName = "ID")
	private Issue issue;
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(User addedBy) {
		this.addedBy = addedBy;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getCommet() {
		return commet;
	}

	public void setCommet(String commet) {
		this.commet = commet;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", addedBy=" + addedBy + ", addDate="
				+ addDate + ", commet=" + commet + ", issue=" + issue + "]";
	}
	
	
}
