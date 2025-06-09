package com.vasworks.npc.struts.agent;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.vasworks.npc.model.BirthAttestation;
import com.vasworks.npc.model.BirthCert;
import com.vasworks.npc.model.EducationLevel;
import com.vasworks.npc.model.Father;
import com.vasworks.npc.model.HomeAddress;
import com.vasworks.npc.model.Informant;
import com.vasworks.npc.model.Mother;
import com.vasworks.npc.model.Occupation;
import com.vasworks.npc.model.RequestReasonA;
import com.vasworks.npc.model.RequestReasonB;
import com.vasworks.npc.struts.AgentAction;
import com.vasworks.npc.values.BirthPlace;
import com.vasworks.npc.values.BirthType;
import com.vasworks.npc.values.Gender;
import com.vasworks.npc.values.MaritalStatus;

public class BirthAttnAction extends AgentAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3044965041941202832L;
	
	private Long attnId;
	
	private Long informantId;
	
	private Long fatherId;
	
	private Long motherId;
	
	private String informantSearchField;
	
	private String fatherSearchField;
	
	private String motherSearchField;
	
	private String addressSearchField;
	
	private String workAddressSearchField;
	
	private String occupationSearchField;
	
	private String educationLevelSearchField;

	private String reqOrgAddressSearchField;

	private String countrySearchField;
	
	private String param;
	
	private BirthAttestation entity;
	
	private List<BirthAttestation> birthAttns;
	
	private Long reqOrgAddressId;

	private Long countryStateId;

	private Long lgaId;

	private Long addressId;

	private Long workAddressId;

	private Long educationLevelId;

	private Long occupationId;

	private Long requestReasonAId;

	private Long requestReasonBId;

	private String countryId;
	
	private File photo;
	
	private String photoContentType;
	
	private String photoFileName;
	
	private Long photoId;
		
	@Override
	public void prepare() {
	}

	@Override
	public String execute() {
		session.put("birth_attn_id", null);
		
		return super.execute();
	}
	
	public String load() {
		if(attnId != null) {
			if(entity == null) {
				entity = (BirthAttestation) agentService.find(attnId, BirthCert.class);
			}
		}
		return SUCCESS;
	}
	
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "entity.firstName", message = "'First Name' is required."),
				@RequiredStringValidator(fieldName = "entity.lastName", message = "'Last Name' is required."),
				@RequiredStringValidator(fieldName = "entity.certificateNumber", message = "'Certificate Number' is required."),
				@RequiredStringValidator(fieldName = "entity.town", message = "'Birth Town' is required."),
				@RequiredStringValidator(fieldName = "entity.registrationCenter", message = "'Registration Center' is required."),
				@RequiredStringValidator(fieldName = "entity.registrationTown", message = "'Registration Town' is required."),
				@RequiredStringValidator(fieldName = "entity.registrationCenter", message = "'Registration Center' is required."),
				@RequiredStringValidator(fieldName = "entity.lastName", message = "'Last Name' is required.")
			},
			requiredFields = {
				@RequiredFieldValidator(fieldName = "entity.birthDate", message = "'Date of Birth' is required."),
				@RequiredFieldValidator(fieldName = "entity.gender", message = "'Gender' is required."),
				@RequiredFieldValidator(fieldName = "fatherId", message = "'Father' is required."),
				@RequiredFieldValidator(fieldName = "motherId", message = "'Mother' is required."),
				@RequiredFieldValidator(fieldName = "informantId", message = "'Informant' is required."),
				@RequiredFieldValidator(fieldName = "requestReasonAId", message = "'Reason for Request (A)' is required."),
				@RequiredFieldValidator(fieldName = "requestReasonBId", message = "'Reason for Request (B)' is required."),
				@RequiredFieldValidator(fieldName = "addressId", message = "'Address' is required."),
				@RequiredFieldValidator(fieldName = "motherId", message = "'Mother' is required."),
				@RequiredFieldValidator(fieldName = "entity.birthPlace", message = "'Birth Place' is required."),
				@RequiredFieldValidator(fieldName = "entity.birthType", message = "'Birth Type' is required.")
			}
		)
	public String save() {
		birthAttns = agentService.saveBirthAttn(attnId, entity, fatherId, motherId, informantId, countryStateId, lgaId, addressId, workAddressId, educationLevelId, occupationId, requestReasonAId, requestReasonBId, countryId, photo, photoId, getUser().getId());
		
		entity = new BirthAttestation();
		attnId = null;
		informantId = null;
		fatherId = null;
		motherId = null;
		
		session.remove("birth_attn_id");
		
		addActionMessage("Birth certificate successfully saved.");
		
		return "list";
	}
	
	public String initNew() {
		entity = new BirthAttestation();
		
		session.remove("birth_attn_id");
		attnId = null;
		informantId = null;
		fatherId = null;
		motherId = null;
		
		return SUCCESS;
	}
	
	public String select() {
		if(attnId != null) {
			entity = (BirthAttestation) agentService.find(attnId, BirthAttestation.class);
			
			attnId = entity.getId();
			
			Informant informant = entity.getInformant();
			informantId = informant.getId();
			informantSearchField = agentService.stringifyInformant(informant);
			
			Father father = entity.getFather();
			fatherId = father.getId();
			fatherSearchField = agentService.stringifyFather(father);
			
			Mother mother = entity.getMother();
			motherId = mother.getId();
			motherSearchField = agentService.stringifyMother(mother);
			
			Occupation occupation = entity.getOccupation();
			occupationId = occupation.getId();
			occupationSearchField = agentService.stringifyOccupation(occupation);
			
			EducationLevel educationLevel = entity.getEducationLevel();
			educationLevelId = educationLevel.getId();		
			educationLevelSearchField = agentService.stringifyEducationLevel(educationLevel);
			
			HomeAddress address = entity.getAddress();
			addressId = address.getId();
			addressSearchField = agentService.stringifyAddress(address);
			
			HomeAddress workAddress = entity.getWorkAddress();
			workAddressId = workAddress.getId();
			workAddressSearchField = agentService.stringifyAddress(address);
			
			session.put("birth_attn_id", attnId);
		}
		return SUCCESS;
	}
	
	public String remove() {
		if(attnId != null) {
			agentService.remove(attnId, BirthCert.class);
		}
		
		return list();
	}
	
	public String list() {
		birthAttns = (List<BirthAttestation>) agentService.listBirthAttns(new Date());
		
		return SUCCESS;
	}
	
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "param", message = "'Search Parameter' is required.")
			}
		)
	public String search() {
		birthAttns = agentService.searchBirthAttns(param);
		
		return SUCCESS;
	}
	
	public Long getAttnId() {
		return attnId;
	}

	public void setAttnId(Long certId) {
		this.attnId = certId;
	}

	public Long getInformantId() {
		return informantId;
	}

	public void setInformantId(Long informantId) {
		this.informantId = informantId;
	}

	public Long getFatherId() {
		return fatherId;
	}

	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}

	public Long getMotherId() {
		return motherId;
	}

	public void setMotherId(Long motherId) {
		this.motherId = motherId;
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

	public String getInformantSearchField() {
		return informantSearchField;
	}

	public void setInformantSearchField(String informantSearchField) {
		this.informantSearchField = informantSearchField;
	}

	public String getFatherSearchField() {
		return fatherSearchField;
	}

	public void setFatherSearchField(String fatherSearchField) {
		this.fatherSearchField = fatherSearchField;
	}

	public String getMotherSearchField() {
		return motherSearchField;
	}

	public void setMotherSearchField(String motherSearchField) {
		this.motherSearchField = motherSearchField;
	}

	public String getAddressSearchField() {
		return addressSearchField;
	}

	public void setAddressSearchField(String addressSearchField) {
		this.addressSearchField = addressSearchField;
	}

	public String getWorkAddressSearchField() {
		return workAddressSearchField;
	}

	public void setWorkAddressSearchField(String workAddressSearchField) {
		this.workAddressSearchField = workAddressSearchField;
	}

	public String getOccupationSearchField() {
		return occupationSearchField;
	}

	public void setOccupationSearchField(String occupationSearchField) {
		this.occupationSearchField = occupationSearchField;
	}

	public String getEducationLevelSearchField() {
		return educationLevelSearchField;
	}

	public void setEducationLevelSearchField(String educationLevelSearchField) {
		this.educationLevelSearchField = educationLevelSearchField;
	}

	public String getReqOrgAddressSearchField() {
		return reqOrgAddressSearchField;
	}

	public void setReqOrgAddressSearchField(String reqOrgAddressSearchField) {
		this.reqOrgAddressSearchField = reqOrgAddressSearchField;
	}

	public String getCountrySearchField() {
		return countrySearchField;
	}

	public void setCountrySearchField(String countrySearchField) {
		this.countrySearchField = countrySearchField;
	}

	public Long getReqOrgAddressId() {
		return reqOrgAddressId;
	}

	public void setReqOrgAddressId(Long reqOrgAddressId) {
		this.reqOrgAddressId = reqOrgAddressId;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public BirthAttestation getEntity() {
		return entity;
	}

	public void setEntity(BirthAttestation entity) {
		this.entity = entity;
	}

	public List<BirthAttestation> getBirthAttns() {
		return birthAttns;
	}

	public Gender[] getGenderLov() {
		return Gender.values();
	}

	public MaritalStatus[] getMaritalStatusLov() {
		return MaritalStatus.values();
	}

	public BirthPlace[] getBirthPlaceLov() {
		return BirthPlace.values();
	}

	public BirthType[] getBirthTypeLov() {
		return BirthType.values();
	}
	
	@SuppressWarnings("unchecked")
	public List<RequestReasonA> getRequestReasonALov() {
		return (List<RequestReasonA>) agentService.list(RequestReasonA.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<RequestReasonB> getRequestReasonBLov() {
		return (List<RequestReasonB>) agentService.list(RequestReasonB.class);
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Long getWorkAddressId() {
		return workAddressId;
	}

	public void setWorkAddressId(Long workAddressId) {
		this.workAddressId = workAddressId;
	}

	public Long getEducationLevelId() {
		return educationLevelId;
	}

	public void setEducationLevelId(Long educationLevelId) {
		this.educationLevelId = educationLevelId;
	}

	public Long getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(Long occupationId) {
		this.occupationId = occupationId;
	}

	public Long getRequestReasonAId() {
		return requestReasonAId;
	}

	public void setRequestReasonAId(Long requestReasonAId) {
		this.requestReasonAId = requestReasonAId;
	}

	public Long getRequestReasonBId() {
		return requestReasonBId;
	}

	public void setRequestReasonBId(Long requestReasonBId) {
		this.requestReasonBId = requestReasonBId;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
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
