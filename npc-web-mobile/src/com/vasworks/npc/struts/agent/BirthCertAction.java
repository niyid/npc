package com.vasworks.npc.struts.agent;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.vasworks.npc.model.BirthCert;
import com.vasworks.npc.model.Father;
import com.vasworks.npc.model.Informant;
import com.vasworks.npc.model.Kinship;
import com.vasworks.npc.model.Mother;
import com.vasworks.npc.struts.AgentAction;
import com.vasworks.npc.values.BirthPlace;
import com.vasworks.npc.values.BirthType;
import com.vasworks.npc.values.Gender;

public class BirthCertAction extends AgentAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3044965041941202832L;
	
	private String certId;
	
	private Long informantId;
	
	private Long fatherId;
	
	private Long motherId;
	
	private String informantSearchField;
	
	private String fatherSearchField;
	
	private String motherSearchField;
	
	private String param;
	
	private BirthCert entity;
	
	private List<BirthCert> birthCerts;

	private Long countryStateId;

	private Long lgaId;
	
	private Long kinshipId;
	
	@Override
	public void prepare() {
	}

	@Override
	public String execute() {
		session.put("birth_cert_id", null);
		
		return super.execute();
	}
	
	public String load() {
		if(certId != null) {
			if(entity == null) {
				entity = (BirthCert) agentService.find(certId, BirthCert.class);
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
				@RequiredFieldValidator(fieldName = "entity.birthPlace", message = "'Birth Place' is required."),
				@RequiredFieldValidator(fieldName = "entity.birthType", message = "'Birth Type' is required.")
			}
		)
	public String save() {
		birthCerts = agentService.saveBirthCert(entity, fatherId, motherId, informantId, countryStateId, lgaId, kinshipId, getUser().getId());
		
		entity = new BirthCert();
		certId = null;
		informantId = null;
		fatherId = null;
		motherId = null;
		
		session.remove("birth_cert_id");
		
		addActionMessage("Birth certificate successfully saved.");
		
		return "list";
	}
	
	public String initNew() {
		entity = new BirthCert();
		
		session.remove("birth_cert_id");
		certId = null;
		informantId = null;
		fatherId = null;
		motherId = null;
		
		return SUCCESS;
	}
	
	public String select() {
		if(certId != null) {
			entity = (BirthCert) agentService.find(certId, BirthCert.class);
			
			certId = entity.getCertificateNumber();
			
			Informant informant = entity.getInformant();
			informantId = informant.getId();
			informantSearchField = agentService.stringifyInformant(informant);
			
			Father father = entity.getFather();
			fatherId = father.getId();
			fatherSearchField = agentService.stringifyFather(father);
			
			Mother mother = entity.getMother();
			motherId = mother.getId();
			motherSearchField = agentService.stringifyMother(mother);
			
			session.put("birth_cert_id", certId);
		}
		return SUCCESS;
	}
	
	public String remove() {
		if(certId != null) {
			agentService.remove(certId, BirthCert.class);
		}
		
		return list();
	}
	
	public String list() {
		birthCerts = (List<BirthCert>) agentService.listBirthCerts(new Date());
		
		return SUCCESS;
	}
	
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "param", message = "'Search Parameter' is required.")
			}
		)
	public String search() {
		birthCerts = agentService.searchBirthCerts(param);
		
		return SUCCESS;
	}
	
	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
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

	public Long getKinshipId() {
		return kinshipId;
	}

	public void setKinshipId(Long kinshipId) {
		this.kinshipId = kinshipId;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public BirthCert getEntity() {
		return entity;
	}

	public void setEntity(BirthCert entity) {
		this.entity = entity;
	}

	public List<BirthCert> getBirthCerts() {
		return birthCerts;
	}

	public Gender[] getGenderLov() {
		return Gender.values();
	}
	
	@SuppressWarnings("unchecked")
	public List<Kinship> getKinshipLov() {
		return (List<Kinship>) agentService.list(Kinship.class);
	}
	
	public BirthPlace[] getBirthPlaceLov() {
		return BirthPlace.values();
	}
	
	public BirthType[] getBirthTypeLov() {
		return BirthType.values();
	}
}
