package com.vasworks.npc.struts.agent;

import java.util.List;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.vasworks.npc.model.HomeAddress;
import com.vasworks.npc.struts.AgentAction;

public class AddressAction extends AgentAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3044965041941202832L;
	
	private Long addressId;
	
	private Long countryStateId;
	
	private String countryStateSearchField;
	
	private String param;
	
	private HomeAddress entity;
	
	private List<HomeAddress> addresses;
	
	@Override
	public void prepare() {
	}

	@Override
	public String execute() {
		session.put("address_id", null);
		
		return super.execute();
	}
	
	public String load() {
		if(addressId != null) {
			if(entity == null) {
				entity = (HomeAddress) agentService.find(addressId, HomeAddress.class);
			}
		}
		return SUCCESS;
	}
	
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "entity.areaName1", message = "'Area Name' is required."),
				@RequiredStringValidator(fieldName = "entity.streetName", message = "'Street Name' is required."),
				@RequiredStringValidator(fieldName = "entity.propertyNumber", message = "'Property Number' is required.")
			},
			requiredFields = {
				@RequiredFieldValidator(fieldName = "countryStateId", message = "'State' is required.")
			}
		)
	public String save() {
		addresses = agentService.saveHomeAddress(addressId, entity, countryStateId, getUser().getId());
		
		entity = new HomeAddress();
		addressId = null;
		countryStateId = null;
		
		session.remove("address_id");
		
		addActionMessage("Address successfully saved.");
		
		return "list";
	}
	
	public String initNew() {
		entity = new HomeAddress();

		addressId = null;
		countryStateId = null;
		
		session.remove("address_id");
		
		return SUCCESS;
	}
	
	public String select() {
		if(addressId != null) {
			entity = (HomeAddress) agentService.find(addressId, HomeAddress.class);
			
			addressId = entity.getId();
			
			countryStateId = entity.getCountryState().getId();
			
			countryStateSearchField = entity.getCountryState().getStateName();
			
			session.put("address_id", addressId);
		}
		return SUCCESS;
	}
	
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "param", message = "'Search Parameter' is required.")
			}
		)
	public String search() {
		addresses = agentService.searchAddresses(param);
		
		return SUCCESS;
	}
	
	public String remove() {
		if(addressId != null) {
			agentService.remove(addressId, HomeAddress.class);
		}
		
		return list();
	}
	
	@SuppressWarnings("unchecked")
	public String list() {
		addresses = (List<HomeAddress>) agentService.list(HomeAddress.class);
		
		return SUCCESS;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Long getCountryStateId() {
		return countryStateId;
	}

	public void setCountryStateId(Long countryStateId) {
		this.countryStateId = countryStateId;
	}

	public String getCountryStateSearchField() {
		return countryStateSearchField;
	}

	public void setCountryStateSearchField(String countryStateSearchField) {
		this.countryStateSearchField = countryStateSearchField;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public HomeAddress getEntity() {
		return entity;
	}

	public void setEntity(HomeAddress entity) {
		this.entity = entity;
	}

	public List<HomeAddress> getAddresses() {
		return addresses;
	}

}
