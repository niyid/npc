package com.vasworks.npc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.vasworks.npc.values.Gender;
import com.vasworks.npc.values.MaritalStatus;

@Entity
public class DeathCert implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4548636500968388446L;
	
	private Long id;
	
	private Date deathDate;
	
	private String deathPlace;
	
	private String deathCause;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private String maidenName;
	
	private Date birthDate;
	
	private String birthPlace;
	
	private Gender gender;
	
	private Date regDate;
	
	private MaritalStatus maritalStatus;
	
	private HomeAddress address;
	
	private Informant informant;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	@Lob
	public String getDeathPlace() {
		return deathPlace;
	}

	public void setDeathPlace(String deathPlace) {
		this.deathPlace = deathPlace;
	}

	@Lob
	public String getDeathCause() {
		return deathCause;
	}

	public void setDeathCause(String deathCause) {
		this.deathCause = deathCause;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}

	@Temporal(TemporalType.DATE)
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Lob
	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.DATE)
	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	@ManyToOne
	public HomeAddress getAddress() {
		return address;
	}

	public void setAddress(HomeAddress address) {
		this.address = address;
	}

	@ManyToOne
	public Informant getInformant() {
		return informant;
	}

	public void setInformant(Informant informant) {
		this.informant = informant;
	}
}
