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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.training.siarhei_baradzionak.domain.beans.project.BuildProject;
import org.training.siarhei_baradzionak.domain.beans.project.Project;
import org.training.siarhei_baradzionak.domain.beans.users.User;

import com.google.inject.internal.asm.Type;


@Entity
@Table(name="ISSUE")
public class Issue implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="ID", unique = true, nullable = false)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="PRIORITY")
	private Priority priority;
	
	@ManyToOne
	@JoinColumn(name="RESOLUTION")
	@NotFound(action=NotFoundAction.IGNORE)
	private Resolution resolution;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TYPE")
	private org.training.siarhei_baradzionak.domain.beans.issue.Type type;
	
	@Column(name="SUMMARY")
	private String summary;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", unique = false, nullable = false)
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_DATE", unique = false, nullable = false)
	private Date modifyDate;
	
	
	@ManyToOne
	@JoinColumn(name="ASSIGNEE")
	@NotFound(action = NotFoundAction.IGNORE)
	private User assignee;
	
	@ManyToOne
	@JoinColumn(name="CREATED_BY")
	private User createdby;
	
	@ManyToOne
	@JoinColumn(name="MODIFIED_BY")
	private User modifiedBy;
	
	@ManyToOne
	@JoinColumn(name="IDPROJECT")
	private Project project;
	
	@ManyToOne
	@JoinColumn(name = "IDSTATUS")
	private Status status;

	@ManyToOne
	@JoinColumn(name = "IDBUILD")
	private BuildProject build;
	
	public Issue() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Resolution getResolution() {
		return resolution;
	}

	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}

	public org.training.siarhei_baradzionak.domain.beans.issue.Type getType() {
		return type;
	}

	public void setType(org.training.siarhei_baradzionak.domain.beans.issue.Type curType) {
		this.type = curType;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public User getCreatedby() {
		return createdby;
	}

	public void setCreatedby(User createdby) {
		this.createdby = createdby;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public BuildProject getBuild() {
		return build;
	}

	public void setBuild(BuildProject build) {
		this.build = build;
	}

	@Override
	public String toString() {
		return "Issue [id=" + id + ", priority=" + priority + ", resolution="
				+ resolution + ", type=" + type + ", summary=" + summary
				+ ", description=" + description + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ", assignee=" + assignee
				+ ", createdby=" + createdby + ", modifiedBy=" + modifiedBy
				+ ", project=" + project + ", status=" + status + ", build="
				+ build + "]";
	}
	
	
}
