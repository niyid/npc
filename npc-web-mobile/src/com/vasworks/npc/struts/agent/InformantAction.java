package com.vasworks.npc.struts.agent;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.vasworks.npc.model.HomeAddress;
import com.vasworks.npc.model.Informant;
import com.vasworks.npc.struts.AgentAction;

public class InformantAction extends AgentAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3044965041941202832L;
	
	private Long informantId;
	
	private Long addressId;
	
	private Long signatureId;
	
	private Long photoId;
	
	private String addressSearchField;
	
	private String param;
	
	private Informant entity;
	
	private String signature;
	
	private boolean signed;
	
	private File photo;
	
	private String photoContentType;
	
	private String photoFileName;
	
	private List<Informant> informants;
	
	@Override
	public void prepare() {
	}

	@Override
	public String execute() {
		session.put("informant_id", null);
		
		return super.execute();
	}
	
	public String load() {
		if(informantId != null) {
			if(entity == null) {
				entity = (Informant) agentService.find(informantId, Informant.class);
			}
		}
		return SUCCESS;
	}
	
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "entity.firstName", message = "'First Name' is required."),
				@RequiredStringValidator(fieldName = "entity.lastName", message = "'Last Name' is required.")
			},
			requiredFields = {
				@RequiredFieldValidator(fieldName = "photo", message = "A passport-sized photo must be selected.")	
			}
		)
	public String save() {
		informants = agentService.saveInformant(informantId, entity, addressId, photo, photoId, signature, signatureId, getUser().getId());
		
		entity = new Informant();
		informantId = null;
		addressId = null;
		addressSearchField = null;
		signatureId = null;
		photoId = null;
		
		session.remove("informant_id");
		
		addActionMessage("Informant successfully saved.");
		
		return "list";
	}
	
	
	
	@Override
	public void validate() {
		if(!signed) {
			addActionError("Applicant must append his signature.");
		}
	}

	public String initNew() {
		entity = new Informant();
		informantId = null;
		addressId = null;
		addressSearchField = null;
		signatureId = null;
		photoId = null;
		
		session.remove("informant_id");
		
		return SUCCESS;
	}
	
	public String select() {
		if(informantId != null) {
			entity = (Informant) agentService.find(informantId, Informant.class);
			
			session.put("informant_id", informantId);
			
			HomeAddress address = entity.getAddress();
			addressSearchField = agentService.stringifyAddress(address);
			addressId = entity.getAddress().getId();
			signatureId = entity.getSignature() != null ? entity.getSignature().getId() : null;
			photoId = entity.getPhoto() != null ? entity.getPhoto().getId() : null;
		}
		return SUCCESS;
	}
	
	public String remove() {
		if(informantId != null) {
			agentService.remove(informantId, Informant.class);
		}
		
		return list();
	}
	
	public String list() {
		informants = (List<Informant>) agentService.listInformants(new Date());
		
		return SUCCESS;
	}
	
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "param", message = "'Search Parameter' is required.")
			}
		)
	public String search() {
		informants = agentService.searchInformants(param);
		
		return SUCCESS;
	}
	
	public Long getInformantId() {
		return informantId;
	}

	public void setInformantId(Long informantId) {
		this.informantId = informantId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Long getSignatureId() {
		return signatureId;
	}

	public void setSignatureId(Long signatureFileId) {
		this.signatureId = signatureFileId;
	}

	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoFileId) {
		this.photoId = photoFileId;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getAddressSearchField() {
		return addressSearchField;
	}

	public void setAddressSearchField(String addressSearchField) {
		this.addressSearchField = addressSearchField;
	}
	
	public Informant getEntity() {
		return entity;
	}

	public void setEntity(Informant entity) {
		this.entity = entity;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public boolean getSigned() {
		return signed;
	}

	public void setSigned(boolean signed) {
		this.signed = signed;
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

	public List<Informant> getInformants() {
		return informants;
	}
	
	@SuppressWarnings("unchecked")
	public List<HomeAddress> getAddressLov() {
		return (List<HomeAddress>) agentService.list(HomeAddress.class);
	}
}
