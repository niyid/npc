package com.vasworks.npc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class HomeAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3994464527228660194L;

	private Long id;
	
	private String areaName1;
	
	private String areaName2;
	
	private String streetName;
	
	private String propertyNumber;
	
	private CountryState countryState;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAreaName1() {
		return areaName1;
	}

	public void setAreaName1(String areaName1) {
		this.areaName1 = areaName1;
	}

	public String getAreaName2() {
		return areaName2;
	}

	public void setAreaName2(String areaName2) {
		this.areaName2 = areaName2;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getPropertyNumber() {
		return propertyNumber;
	}

	public void setPropertyNumber(String propertyNumber) {
		this.propertyNumber = propertyNumber;
	}

	@ManyToOne
	public CountryState getCountryState() {
		return countryState;
	}

	public void setCountryState(CountryState countryState) {
		this.countryState = countryState;
	}
}
