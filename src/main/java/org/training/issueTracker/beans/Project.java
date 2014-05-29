package org.training.issueTracker.beans;

import java.util.List;

public class Project {
	private int idProject;
	private String name;
	private  List<Build_Found> builds;
	private String description;
	private User manager;
	
	
	
	public Project(int idProject, String name, List<Build_Found> build,
			String description, User manager) {
		super();
		this.idProject = idProject;
		this.name = name;
		this.builds = build;
		this.description = description;
		this.manager = manager;
	}
	
	public int getIdProject() {
		return idProject;
	}
	
	public List<Build_Found> getBuilds() {
		return builds;
	}

	public void setBuilds(List<Build_Found> builds) {
		this.builds = builds;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getManager() {
		return manager;
	}
	public void setManager(User manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Project [idProject=" + idProject + ", name=" + name
				+ ", builds=" + builds + ", description=" + description
				+ ", manager=" + manager + "]";
	}
	
	
	
}
