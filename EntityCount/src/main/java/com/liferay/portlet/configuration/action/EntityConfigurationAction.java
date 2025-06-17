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

@Component(configurationPid = EntityCountPortletKeys.CONFIGURATION_ID, configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true, property = {
		"javax.portlet.name=" + EntityCountPortletKeys.ENTITYCOUNT, }, service = ConfigurationAction.class)
public class EntityConfigurationAction extends DefaultConfigurationAction {

	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception {

		String displayType = ParamUtil.get(actionRequest, "displayType", "users");

		boolean showUsers = displayType.equals("Users");
		boolean showImages = displayType.equals("Images");
		boolean showArticles = displayType.equals("Artilces");
		boolean showEmployeesCount = displayType.equals("Employees");
		boolean showCategoiresCount = displayType.equals("Categories");

		setPreference(actionRequest, EntityCountPortletKeys.SHOW_ARTICLES, String.valueOf(showArticles));
		setPreference(actionRequest, EntityCountPortletKeys.SHOW_IMAGES, String.valueOf(showImages));
		setPreference(actionRequest, EntityCountPortletKeys.SHOW_USERS, String.valueOf(showUsers));
		setPreference(actionRequest, EntityCountPortletKeys.SHOW_EMPLOYEES, String.valueOf(showEmployeesCount));
		setPreference(actionRequest, EntityCountPortletKeys.SHOW_CATEGORIES, String.valueOf(showCategoiresCount));

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		super.include(portletConfig, request, response);
	}

}
