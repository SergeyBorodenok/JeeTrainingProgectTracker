package org.training.siarhei_baradzionak.domain.beans.issue;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.training.siarhei_baradzionak.domain.beans.users.User;

@Entity
@Table(name="ATTACHMENT")
public class Attachment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="ID",unique = true, nullable = false)
	private int id;
	
	@Column(name="ADDED_BY", unique = false, nullable = false)
	private User addedUser;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="ADD_DATE", unique = false, nullable = false)
	private Date addDate;
	
	@Column(name="ATTACHMENT_LINK",unique = true, nullable = false)
	private String linkAttachment;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID",nullable = false)
	private Issue issue;
	
	
	public Attachment() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public User getAddedUser() {
		return addedUser;
	}


	public void setAddedUser(User addedUser) {
		this.addedUser = addedUser;
	}


	public Date getAddDate() {
		return addDate;
	}


	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}


	public String getLinkAttachment() {
		return linkAttachment;
	}


	public void setLinkAttachment(String linkAttachment) {
		this.linkAttachment = linkAttachment;
	}


	public Issue getIssue() {
		return issue;
	}


	public void setIssue(Issue issue) {
		this.issue = issue;
	}


	@Override
	public String toString() {
		return "Attachment [id=" + id + ", addedUser=" + addedUser
				+ ", addDate=" + addDate + ", linkAttachment=" + linkAttachment
				+ ", issue=" + issue + "]";
	}
	
	

}
