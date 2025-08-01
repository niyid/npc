package com.vasworks.npc.struts.json;

import java.io.ByteArrayInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vasworks.npc.struts.AgentAction;

public class EducationLevelSelectionAction extends AgentAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7456957464885980469L;
	
	private static final Log LOG = LogFactory.getLog(EducationLevelSelectionAction.class);
	
	private ByteArrayInputStream jsonStream;
	
	private String param;
	
	public String getJSON() {
	    return execute();
	}
	
	@Override
	public String execute() {
		String jsonString = agentService.autocompleteEducationLevels(param);
		
		LOG.debug("jsonString: " + jsonString);
		
		jsonStream = new ByteArrayInputStream(jsonString.getBytes());
		
		return SUCCESS;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
	public ByteArrayInputStream getJsonStream() {
		return jsonStream;
	}
}
