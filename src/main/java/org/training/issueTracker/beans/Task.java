package org.training.issueTracker.beans;

import java.sql.Date;

public class Task {
	private int id;
	private Date createDate;
	private Date modifyDate;
	private String createdUser;
	private String modifyUser;
	private String summary;
	private String description;
	private String status;
	private String resolution;
	private String type;
	private String priority;
	private String project;
	private String buildFound;
	private String assignee;
	
	public Task(int id, Date createDate, Date modifyDate, String createdUser,
			String modifyUser, String summary, String description,
			String status, String resolution, String type, String priority,
			String project, String buildFound, String assignee) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.createdUser = createdUser;
		this.modifyUser = modifyUser;
		this.summary = summary;
		this.description = description;
		this.status = status;
		this.resolution = resolution;
		this.type = type;
		this.priority = priority;
		this.project = project;
		this.buildFound = buildFound;
		this.assignee = assignee;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getBuildFound() {
		return buildFound;
	}
	public void setBuildFound(String buildFound) {
		this.buildFound = buildFound;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ", createdUser=" + createdUser
				+ ", modifyUser=" + modifyUser + ", summary=" + summary
				+ ", description=" + description + ", status=" + status
				+ ", resolution=" + resolution + ", type=" + type
				+ ", priority=" + priority + ", project=" + project
				+ ", buildFound=" + buildFound + ", assignee=" + assignee + "]";
	}
	
}
