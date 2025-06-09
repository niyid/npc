package com.vasworks.npc.struts.agent;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.vasworks.npc.model.CountryState;
import com.vasworks.npc.model.EducationLevel;
import com.vasworks.npc.model.Ethnicity;
import com.vasworks.npc.model.Father;
import com.vasworks.npc.model.HomeAddress;
import com.vasworks.npc.model.LocalGovArea;
import com.vasworks.npc.model.Occupation;
import com.vasworks.npc.struts.AgentAction;
import com.vasworks.npc.values.MaritalStatus;

public class FatherAction extends AgentAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3044965041941202832L;
	
	private Long fatherId;
	
	private Long occupationId;
	
	private Long educationLevelId;
	
	private Long ethnicityId;
	
	private Long countryStateId;
	
	private Long lgaId;	
	
	private Long addressId;
	
	private String param;
	
	private String occupationSearchField;
	
	private String countryStateSearchField;
	
	private String ethnicitySearchField;
	
	private String educationLevelSearchField;
	
	private String lgaSearchField;
	
	private String addressSearchField;
	
	private Father entity;
	
	private File photo;
	
	private String photoContentType;
	
	private String photoFileName;
	
	private Long photoId;
	
	private List<Father> fathers;
	
	@Override
	public void prepare() {
	}

	@Override
	public String execute() {
		session.put("father_id", null);
		
		return super.execute();
	}
	
	public String load() {
		if(fatherId != null) {
			if(entity == null) {
				entity = (Father) agentService.find(fatherId, Father.class);
			}
		}
		return SUCCESS;
	}
	
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "entity.firstName", message = "'First Name' is required."),
				@RequiredStringValidator(fieldName = "entity.lastName", message = "'Last Name' is required."),
				@RequiredStringValidator(fieldName = "entity.phoneNumber", message = "'Phone Number' is required.")
			},
			requiredFields = {
				@RequiredFieldValidator(fieldName = "entity.birthDate", message = "'Date of Birth' is required."),
				@RequiredFieldValidator(fieldName = "entity.gender", message = "'Gender' is required."),
				@RequiredFieldValidator(fieldName = "occupationId", message = "'Occupation' is required."),
				@RequiredFieldValidator(fieldName = "educationLevelId", message = "'Education Level' is required."),
				@RequiredFieldValidator(fieldName = "ethnicityId", message = "'Ethnicity' is required."),
				@RequiredFieldValidator(fieldName = "countryStateId", message = "'State' is required."),
				@RequiredFieldValidator(fieldName = "lgaId", message = "'LGA' is required."),
				@RequiredFieldValidator(fieldName = "motherId", message = "'Mother' is required."),
				@RequiredFieldValidator(fieldName = "informantId", message = "'Informant' is required."),
				@RequiredFieldValidator(fieldName = "entity.birthPlace", message = "'Birth Place' is required."),
				@RequiredFieldValidator(fieldName = "entity.birthType", message = "'Birth Type' is required.")
			}
		)
	public String save() {
		fathers = agentService.saveFather(fatherId, entity, occupationId, educationLevelId, ethnicityId, countryStateId, lgaId, photo, photoId, getUser().getId());
		
		entity = new Father();
		fatherId = null;
		occupationId = null;
		
		session.remove("father_id");
		
		addActionMessage("Father successfully saved.");
		
		return "list";
	}
	
	public String initNew() {
		entity = new Father();
		fatherId = null;
		occupationId = null;
		
		session.remove("father_id");
		
		return SUCCESS;
	}
	
	public String select() {
		if(fatherId != null) {
			entity = (Father) agentService.find(fatherId, Father.class);
			
			fatherId = entity.getId();
			
			Occupation occupation = entity.getOccupation();
			occupationId = occupation.getId();
			occupationSearchField = agentService.stringifyOccupation(occupation);
			
			EducationLevel educationLevel = entity.getEducationLevel();
			educationLevelId = educationLevel.getId();		
			educationLevelSearchField = agentService.stringifyEducationLevel(educationLevel);
			
			Ethnicity ethnicity = entity.getEthnicity();
			ethnicityId = ethnicity.getId();			
			educationLevelSearchField = agentService.stringifyEthnicity(ethnicity);
			
			CountryState countryState = entity.getCountryState();
			countryStateId = countryState.getId();	
			countryStateSearchField = agentService.stringifyCountryState(countryState);
			
			LocalGovArea lga = entity.getLga();
			lgaId = lga.getId();	
			educationLevelSearchField = agentService.stringifyLga(lga);
			
			HomeAddress address = entity.getAddress();
			addressId = address.getId();
			addressSearchField = agentService.stringifyAddress(address);
			
			session.put("father_id", fatherId);
		}
		return SUCCESS;
	}
	
	public String remove() {
		if(fatherId != null) {
			agentService.remove(fatherId, Father.class);
		}
		
		return list();
	}
	
	public String list() {
		fathers = (List<Father>) agentService.listFathers(new Date());
		
		return SUCCESS;
	}
	
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "param", message = "'Search Parameter' is required.")
			}
		)
	public String search() {
		fathers = agentService.searchFathers(param);
		
		return SUCCESS;
	}
	
	public Long getFatherId() {
		return fatherId;
	}

	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}

	public Long getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(Long occupationId) {
		this.occupationId = occupationId;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
	public Father getEntity() {
		return entity;
	}

	public void setEntity(Father entity) {
		this.entity = entity;
	}

	public List<Father> getFathers() {
		return fathers;
	}
	
	public MaritalStatus[] getMaritalStatusLov() {
		return MaritalStatus.values();
	}

	public Long getEducationLevelId() {
		return educationLevelId;
	}

	public void setEducationLevelId(Long educationLevelId) {
		this.educationLevelId = educationLevelId;
	}

	public Long getEthnicityId() {
		return ethnicityId;
	}

	public void setEthnicityId(Long ethnicityId) {
		this.ethnicityId = ethnicityId;
	}

	public Long getCountryStateId() {
		return countryStateId;
	}

	public void setCountryStateId(Long countryStateId) {
		this.countryStateId = countryStateId;
	}

	public Long getLgaId() {
		return lgaId;
	}

	public void setLgaId(Long lgaId) {
		this.lgaId = lgaId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getOccupationSearchField() {
		return occupationSearchField;
	}

	public void setOccupationSearchField(String occupationSearchField) {
		this.occupationSearchField = occupationSearchField;
	}

	public String getCountryStateSearchField() {
		return countryStateSearchField;
	}

	public void setCountryStateSearchField(String countryStateSearchField) {
		this.countryStateSearchField = countryStateSearchField;
	}

	public String getEthnicitySearchField() {
		return ethnicitySearchField;
	}

	public void setEthnicitySearchField(String ethnicitySearchField) {
		this.ethnicitySearchField = ethnicitySearchField;
	}

	public String getEducationLevelSearchField() {
		return educationLevelSearchField;
	}

	public void setEducationLevelSearchField(String educationLevelSearchField) {
		this.educationLevelSearchField = educationLevelSearchField;
	}

	public String getLgaSearchField() {
		return lgaSearchField;
	}

	public void setLgaSearchField(String lgaSearchField) {
		this.lgaSearchField = lgaSearchField;
	}

	public String getAddressSearchField() {
		return addressSearchField;
	}

	public void setAddressSearchField(String addressSearchField) {
		this.addressSearchField = addressSearchField;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}
}
