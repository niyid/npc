package com.vasworks.npc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EducationLevel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4143550732577817811L;

	private Long id;
	
	private String description;

	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
