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

@Entity
public class Informant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8977214894583514195L;

	private Long id;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private String phoneNumber;
	
	private String emailAddress;
	
	private String nationalIdNumber;
	
	private HomeAddress address;
	
	private Date regDate;

	private ImageFile photo;
	
	private ImageFile signature;
	
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

	public String getNationalIdNumber() {
		return nationalIdNumber;
	}

	public void setNationalIdNumber(String nationalIdNumber) {
		this.nationalIdNumber = nationalIdNumber;
	}

	@ManyToOne
	public HomeAddress getAddress() {
		return address;
	}

	public void setAddress(HomeAddress address) {
		this.address = address;
	}

	@Temporal(TemporalType.DATE)
	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@OneToOne
	public ImageFile getPhoto() {
		return photo;
	}

	public void setPhoto(ImageFile photo) {
		this.photo = photo;
	}

	@OneToOne
	public ImageFile getSignature() {
		return signature;
	}

	public void setSignature(ImageFile signature) {
		this.signature = signature;
	}
}
