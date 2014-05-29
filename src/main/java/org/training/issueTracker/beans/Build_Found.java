package org.training.issueTracker.beans;

public class Build_Found {
	private int idBuildFound;
	private String name;
	private int projectId;
	public Build_Found(int idBuildFound, String name, int projectId) {
		super();
		this.idBuildFound = idBuildFound;
		this.name = name;
		this.projectId = projectId;
	}
	public int getIdBuildFound() {
		return idBuildFound;
	}
	public void setIdBuildFound(int idBuildFound) {
		this.idBuildFound = idBuildFound;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	@Override
	public String toString() {
		return "Build_Found [idBuildFound=" + idBuildFound + ", name=" + name
				+ ", projectId=" + projectId + "]";
	}
	
}
