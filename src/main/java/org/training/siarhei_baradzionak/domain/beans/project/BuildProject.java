package org.training.siarhei_baradzionak.domain.beans.project;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="BUILDPROJECT")

public class BuildProject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="ID",nullable = false, unique = true)
	private long id;
	
	@Column(name="NAMEBUILD",nullable = false)
	private String nameBuild;
	
	@ManyToOne
	@JoinColumn(name="Id",nullable = false)
	private Project project;
	
	public BuildProject(){
		
	}
	
	
	public BuildProject(long idBuildProject, String nameBuild,
			long idProject) {
		this.id=idBuildProject;
		this.nameBuild = nameBuild;
		this.project.setIdProject(idProject);
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNameBuild() {
		return nameBuild;
	}


	public void setNameBuild(String nameBuild) {
		this.nameBuild = nameBuild;
	}


	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}


	@Override
	public String toString() {
		return "BuildProject [id=" + id + ", nameBuild=" + nameBuild
				+ ", project=" + project + "]";
	}


	
	
	
	
	
}
