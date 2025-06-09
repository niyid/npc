package com.vasworks.npc.struts;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.vasworks.npc.service.AgentService;
import com.vasworks.struts.BaseAction;

public class AgentAction extends BaseAction implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7587429391502948285L;

	protected AgentService agentService;

	protected Map<String, Object> session;

	private SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	private SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm a");

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public void setAgentService(AgentService agentService) {
		this.agentService = agentService;
	}
	
	public Long getBasketId() {
		return session != null ? (Long) session.get("basket_id") : null;
	}

	public String formatDate(Date date) {
		return DATE_FORMAT.format(date);
	}
	
	public String formatDateTime(Date date) {
		return DATETIME_FORMAT.format(date);
	}
}
