package com.vasworks.npc.struts.agent;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.vasworks.npc.model.DeathCert;
import com.vasworks.npc.model.HomeAddress;
import com.vasworks.npc.model.Informant;
import com.vasworks.npc.struts.AgentAction;
import com.vasworks.npc.values.Gender;
import com.vasworks.npc.values.MaritalStatus;

public class DeathCertAction extends AgentAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3044965041941202832L;
	
	private Long certId;
	
	private Long addressId;
	
	private Long informantId;
	
	private String addressSearchField;
	
	private String informantSearchField;
	
	private String param;
	
	private DeathCert entity;
	
	private List<DeathCert> deathCerts;
	
	@Override
	public void prepare() {
	}

	@Override
	public String execute() {
		session.put("death_cert_id", null);
		
		return super.execute();
	}
	
	public String load() {
		if(certId != null) {
			if(entity == null) {
				entity = (DeathCert) agentService.find(certId, DeathCert.class);
			}
		}
		return SUCCESS;
	}
	
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "entity.firstName", message = "'First Name' is required."),
				@RequiredStringValidator(fieldName = "entity.lastName", message = "'Last Name' is required."),
				@RequiredStringValidator(fieldName = "entity.deathPlace", message = "'Place of Death' is required."),
				@RequiredStringValidator(fieldName = "entity.deathCause", message = "'Cause of Death' is required.")
			},
			requiredFields = {
				@RequiredFieldValidator(fieldName = "entity.deathDate", message = "'Date of Death' is required."),
				@RequiredFieldValidator(fieldName = "entity.gender", message = "'Gender' is required."),
				@RequiredFieldValidator(fieldName = "addressId", message = "'Address' is required."),
				@RequiredFieldValidator(fieldName = "informantId", message = "'Informant' is required.")
			}
		)
	public String save() {
		deathCerts = agentService.saveDeathCert(certId, entity, addressId, informantId, getUser().getId());
		
		entity = new DeathCert();
		certId = null;
		addressId = null;
		informantId = null;
		
		session.remove("death_cert_id");
		
		addActionMessage("Death certificate successfully saved.");
		
		return "list";
	}
	
	public String initNew() {
		entity = new DeathCert();
		
		session.remove("death_cert_id");
		certId = null;
		addressId = null;
		informantId = null;
		
		return SUCCESS;
	}
	
	public String select() {
		if(certId != null) {
			entity = (DeathCert) agentService.find(certId, DeathCert.class);
			
			certId = entity.getId();
			
			HomeAddress address = entity.getAddress();
			addressId = address.getId();
			addressSearchField = agentService.stringifyAddress(address);
			
			Informant informant = entity.getInformant();
			informantId = informant.getId();
			informantSearchField = agentService.stringifyInformant(informant);
			
			session.put("death_cert_id", certId);
		}
		return SUCCESS;
	}
	
	public String remove() {
		if(certId != null) {
			agentService.remove(certId, DeathCert.class);
		}
		
		return list();
	}
	
	public String list() {
		deathCerts = (List<DeathCert>) agentService.listDeathCerts(new Date());
		
		return SUCCESS;
	}
	
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "param", message = "'Search Parameter' is required.")
			}
		)
	public String search() {
		deathCerts = agentService.searchDeathCerts(param);
		
		return SUCCESS;
	}
	
	public Long getCertId() {
		return certId;
	}

	public void setCertId(Long certId) {
		this.certId = certId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Long getInformantId() {
		return informantId;
	}

	public void setInformantId(Long informantId) {
		this.informantId = informantId;
	}

	public String getAddressSearchField() {
		return addressSearchField;
	}

	public void setAddressSearchField(String addressSearchField) {
		this.addressSearchField = addressSearchField;
	}

	public String getInformantSearchField() {
		return informantSearchField;
	}

	public void setInformantSearchField(String informantSearchField) {
		this.informantSearchField = informantSearchField;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public DeathCert getEntity() {
		return entity;
	}

	public void setEntity(DeathCert entity) {
		this.entity = entity;
	}

	public List<DeathCert> getDeathCerts() {
		return deathCerts;
	}

	public Gender[] getGenderLov() {
		return Gender.values();
	}
	
	public MaritalStatus[] getMaritalStatusLov() {
		return MaritalStatus.values();
	}
}
