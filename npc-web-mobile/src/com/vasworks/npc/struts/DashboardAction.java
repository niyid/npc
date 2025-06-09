package com.vasworks.npc.struts;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.vasworks.security.Authorize;

// TODO: Auto-generated Javadoc
/**
 * The Class DashboardAction.
 */
public class DashboardAction extends AgentAction {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -45564564536L;
	
	/** The Constant log. */
	private static final Log LOG = LogFactory.getLog(DashboardAction.class);
	
	/* (non-Javadoc)
	 * @see com.vasworks.struts.BaseAction#prepare()
	 */
	@Override
	@SkipValidation
	public void prepare() {
		LOG.debug("prepare()");
		
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@SkipValidation
	public String execute() {
		LOG.debug("execute()");
		
		String forwardId = SUCCESS;
	
		if (Authorize.hasAny("ROLE_OPERATOR")) {
			LOG.info("Redirecting to ROLE_OPERATOR role home screen...");
			session.put("basket_id", getUser().getId());
			forwardId = SUCCESS;
		} else if (Authorize.hasAny("ROLE_MEMBER")) {
			LOG.info("Redirecting to MEMBER role home screen...");
			session.put("basket_id", getUser().getId());
			forwardId = "deathCertHome";
		} else if (Authorize.hasAny("ROLE_ADMIN")) {
			LOG.info("Redirecting to ADMIN role home screen...");
			forwardId = SUCCESS;
		} else if (Authorize.hasAny("ROLE_ANONYMOUS")) {
			LOG.info("Redirecting to ROLE_ANONYMOUS role home screen...");
			forwardId = INPUT;
		}			

		try {
		} catch (Exception e) {
			LOG.error("save", e);
			e.printStackTrace();
			addActionError("An error occurred. Contact your administrator for more information.");
			forwardId = ERROR;
		}
		return forwardId;
	}

}
