package com.liferay.portlet.configuration.action;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.constants.EntityCountPortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

@Component(
	    configurationPid = EntityCountPortletKeys.CONFIGURATION_ID,
	    configurationPolicy = ConfigurationPolicy.OPTIONAL,
	    immediate = true,
	    property = {
	    		"javax.portlet.name=" + EntityCountPortletKeys.ENTITYCOUNT,	    },
	    service = ConfigurationAction.class
	)
	public class EntityConfigurationAction extends DefaultConfigurationAction {
		
	    @Override
	    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest,
	                               ActionResponse actionResponse) throws Exception {

	        String showJournalArticles = ParamUtil.getString(actionRequest, EntityCountPortletKeys.SHOW_ARTICLES, "false");
	        String showDocuments = ParamUtil.getString(actionRequest, EntityCountPortletKeys.SHOW_DOCUMENTS, "false");
	        String showUsers = ParamUtil.getString(actionRequest, EntityCountPortletKeys.SHOW_USERS, "false");

	        setPreference(actionRequest, EntityCountPortletKeys.SHOW_ARTICLES, showJournalArticles);
	        setPreference(actionRequest, EntityCountPortletKeys.SHOW_DOCUMENTS, showDocuments);
	        setPreference(actionRequest, EntityCountPortletKeys.SHOW_USERS, showUsers);

	        super.processAction(portletConfig, actionRequest, actionResponse);
	    }
	    
	    
	    @Override
	    public void include(PortletConfig portletConfig, HttpServletRequest request,
	                        HttpServletResponse response) throws Exception {

	        super.include(portletConfig, request, response);
	    }

	}

