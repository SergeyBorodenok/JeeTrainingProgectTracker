package org.training.siarhei_baradzionak.domain.beans.issue;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="RESOLUTION")
public class Resolution implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@Column(name="RESOLUTION")
	private String resolution;
	
	
	private Set<Issue> issues = new HashSet<Issue>();
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	@Override
	public String toString() {
		return "Resolution [id=" + id + ", resolution=" + resolution + "]";
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "priority")
	public Set<Issue> getIssues() {
		return issues;
	}
	
	

}
