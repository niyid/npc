package com.vasworks.npc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.vasworks.npc.values.MaritalStatus;

@Entity
public class Father implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 916414199750446248L;

	private Long id;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;

	private Date birthDate;
	
	private Date regDate;
	
	private Occupation occupation;

	private ImageFile photo;
	
	private MaritalStatus maritalStatus;
	
	private String phoneNumber;
	
	private String emailAddress;
	
	private String nationalIdNumber;
	
	private HomeAddress address;
	
	private String town;
	
	private LocalGovArea lga;
	
	private CountryState countryState;
	
	private Ethnicity ethnicity;
	
	private EducationLevel educationLevel;
	

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Temporal(TemporalType.DATE)
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@ManyToOne
	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

	@OneToOne
	public ImageFile getPhoto() {
		return photo;
	}

	public void setPhoto(ImageFile photo) {
		this.photo = photo;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@ManyToOne
	public HomeAddress getAddress() {
		return address;
	}

	public void setAddress(HomeAddress address) {
		this.address = address;
	}

	@ManyToOne
	public CountryState getCountryState() {
		return countryState;
	}

	public void setCountryState(CountryState countryState) {
		this.countryState = countryState;
	}

	@ManyToOne
	public Ethnicity getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(Ethnicity ethnicity) {
		this.ethnicity = ethnicity;
	}

	@ManyToOne
	public EducationLevel getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getNationalIdNumber() {
		return nationalIdNumber;
	}

	public void setNationalIdNumber(String nationalIdNumber) {
		this.nationalIdNumber = nationalIdNumber;
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
}
