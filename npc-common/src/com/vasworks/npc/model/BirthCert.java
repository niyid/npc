package com.vasworks.npc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.vasworks.npc.values.BirthPlace;
import com.vasworks.npc.values.BirthType;
import com.vasworks.npc.values.Gender;

@Entity
public class BirthCert implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4548636500968388446L;

	private String registrationCenter;
	
	private String registrationTown;
	
	private String town;
	
	private LocalGovArea lga;
	
	private CountryState countryState;
	
	private String certificateNumber;
	
	private Date registrationDate;
	
	private Date birthDate;
	
	private BirthPlace birthPlace;
	
	private String otherBirthPlace;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private Gender gender;
	
	private BirthType birthType;
	
	private Date regDate;
	
	private Informant informant;
	
	private Father father;
	
	private Mother mother;
	
	private Kinship informantKinship;


	@Temporal(TemporalType.DATE)
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Enumerated(EnumType.STRING)
	public BirthPlace getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(BirthPlace birthPlace) {
		this.birthPlace = birthPlace;
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

	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getRegistrationCenter() {
		return registrationCenter;
	}

	public void setRegistrationCenter(String registrationCenter) {
		this.registrationCenter = registrationCenter;
	}

	public String getRegistrationTown() {
		return registrationTown;
	}

	public void setRegistrationTown(String registrationTown) {
		this.registrationTown = registrationTown;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	@ManyToOne
	public LocalGovArea getLga() {
		return lga;
	}

	public void setLga(LocalGovArea lga) {
		this.lga = lga;
	}

	@ManyToOne
	public CountryState getCountryState() {
		return countryState;
	}

	public void setCountryState(CountryState countryState) {
		this.countryState = countryState;
	}

	@Id
	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	@Temporal(TemporalType.DATE)
	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getOtherBirthPlace() {
		return otherBirthPlace;
	}

	public void setOtherBirthPlace(String otherBirthPlace) {
		this.otherBirthPlace = otherBirthPlace;
	}

	@Enumerated(EnumType.STRING)
	public BirthType getBirthType() {
		return birthType;
	}

	public void setBirthType(BirthType birthType) {
		this.birthType = birthType;
	}

	@Temporal(TemporalType.DATE)
	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@ManyToOne
	public Informant getInformant() {
		return informant;
	}

	public void setInformant(Informant informant) {
		this.informant = informant;
	}

	@ManyToOne
	public Father getFather() {
		return father;
	}

	public void setFather(Father father) {
		this.father = father;
	}

	@ManyToOne
	public Mother getMother() {
		return mother;
	}

	public void setMother(Mother mother) {
		this.mother = mother;
	}

	@ManyToOne
	public Kinship getInformantKinship() {
		return informantKinship;
	}

	public void setInformantKinship(Kinship kinship) {
		this.informantKinship = kinship;
	}
}
