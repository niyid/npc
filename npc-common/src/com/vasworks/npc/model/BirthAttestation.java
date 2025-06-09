package com.vasworks.npc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.vasworks.entity.Country;
import com.vasworks.npc.values.Gender;
import com.vasworks.npc.values.MaritalStatus;

@Entity
public class BirthAttestation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4548636500968388446L;
	
	private Long id;
	
	private Date birthDate;
	
	private String birthPlace;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private Gender gender;
	
	private Boolean employed;
	
	private MaritalStatus maritalStatus;

	private HomeAddress address;

	private HomeAddress workAddress;
	
	private EducationLevel educationLevel;
	
	private Occupation occupation;
	
	private LocalGovArea birthLga;
	
	private CountryState birthState;
	
	private Date regDate;
	
	private Informant informant;
	
	private Father father;
	
	private Mother mother;
	
	private RequestReasonA requestReasonA;

	private RequestReasonB requestReasonB;
	
	private String otherRequestReasonB;
	
	private Country requestCountry;
	
	private ImageFile photo;
	
	private Date attestationDate;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getEmployed() {
		return employed;
	}

	public void setEmployed(Boolean employed) {
		this.employed = employed;
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
	public HomeAddress getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(HomeAddress workAddress) {
		this.workAddress = workAddress;
	}

	@ManyToOne
	public EducationLevel getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	@ManyToOne
	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

	@ManyToOne
	public LocalGovArea getBirthLga() {
		return birthLga;
	}

	public void setBirthLga(LocalGovArea lga) {
		this.birthLga = lga;
	}

	@ManyToOne
	public CountryState getBirthState() {
		return birthState;
	}

	public void setBirthState(CountryState countryState) {
		this.birthState = countryState;
	}

	@ManyToOne
	public RequestReasonA getRequestReasonA() {
		return requestReasonA;
	}

	public void setRequestReasonA(RequestReasonA requestReasonA) {
		this.requestReasonA = requestReasonA;
	}

	@ManyToOne
	public RequestReasonB getRequestReasonB() {
		return requestReasonB;
	}

	public void setRequestReasonB(RequestReasonB requestReasonB) {
		this.requestReasonB = requestReasonB;
	}

	@ManyToOne
	public Country getRequestCountry() {
		return requestCountry;
	}

	public void setRequestCountry(Country requestCountry) {
		this.requestCountry = requestCountry;
	}

	@OneToOne
	public ImageFile getPhoto() {
		return photo;
	}

	public void setPhoto(ImageFile photo) {
		this.photo = photo;
	}

	@Temporal(TemporalType.DATE)
	public Date getAttestationDate() {
		return attestationDate;
	}

	public void setAttestationDate(Date attestationDate) {
		this.attestationDate = attestationDate;
	}

	public String getOtherRequestReasonB() {
		return otherRequestReasonB;
	}

	public void setOtherRequestReasonB(String otherRequestReasonB) {
		this.otherRequestReasonB = otherRequestReasonB;
	}
}
