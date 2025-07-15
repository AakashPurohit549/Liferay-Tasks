package com.liferay.portlet.mvcportlet.portlet;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.constants.EntityCountPortletKeys;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import employee.service.service.EmployeeLocalService;

/**
 * @author ignek
 */
@Component(configurationPid = EntityCountPortletKeys.CONFIGURATION_ID, immediate = true, property = {
		"com.liferay.portlet.display-category=category.sample", "com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=EntityCount",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EntityCountPortletKeys.ENTITYCOUNT, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class EntityCountPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		PortletPreferences prefs = renderRequest.getPreferences();

		boolean showJournalArticles = Boolean
				.parseBoolean(prefs.getValue(EntityCountPortletKeys.SHOW_ARTICLES, "false"));
		boolean showImages = Boolean.parseBoolean(prefs.getValue(EntityCountPortletKeys.SHOW_IMAGES, "false"));
		boolean showUsers = Boolean.parseBoolean(prefs.getValue(EntityCountPortletKeys.SHOW_USERS, "false"));
		boolean showEmployees = Boolean.parseBoolean(prefs.getValue(EntityCountPortletKeys.SHOW_EMPLOYEES, "false"));
		boolean showCategories = Boolean.parseBoolean(prefs.getValue(EntityCountPortletKeys.SHOW_CATEGORIES, "false"));

		int journalCount = Integer.MIN_VALUE;
		int imagesCount = Integer.MIN_VALUE;
		int userCount = Integer.MIN_VALUE;
		long employees = Integer.MIN_VALUE;
		int countOfCatogoery = Integer.MIN_VALUE;

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();

		try {
			if (showJournalArticles) {
				journalCount = journalArticleLocalService.getJournalArticlesCount();
			}
			if (showImages) {
				imagesCount = dLFileEntryLocalService.getDLFileEntriesCount();
			}
			if (showUsers) {
				userCount = userLocalService.getUsersCount();
			}
			if (showEmployees) {
				long CompanyId = (Long) renderRequest.getAttribute("COMPANY_ID");
				renderRequest.setAttribute("CompanyId", CompanyId);

				List<Role> countOfEmp = roleLocalService.getRoles(CompanyId);

				employees = countOfEmp.stream().filter(str -> str.getName().equals("Employee")).count();
			}

			if (showCategories) {
				try {
					AssetVocabulary name = assetVocabularyLocalService.getGroupVocabulary(groupId, "all");
					countOfCatogoery = name.getCategoriesCount();
				} catch (PortalException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			_log.error("Error fetching counts", e);
		}

		renderRequest.setAttribute("journalCount", journalCount);
		renderRequest.setAttribute("imageCount", imagesCount);
		renderRequest.setAttribute("userCount", userCount);
		renderRequest.setAttribute("employees", employees);
		renderRequest.setAttribute("categories", countOfCatogoery);

		renderRequest.setAttribute(EntityCountPortletKeys.SHOW_ARTICLES, showJournalArticles);
		renderRequest.setAttribute(EntityCountPortletKeys.SHOW_IMAGES, showImages);
		renderRequest.setAttribute(EntityCountPortletKeys.SHOW_USERS, showUsers);
		renderRequest.setAttribute(EntityCountPortletKeys.SHOW_EMPLOYEES, showEmployees);
		renderRequest.setAttribute(EntityCountPortletKeys.SHOW_CATEGORIES, showCategories);

		super.doView(renderRequest, renderResponse);
	}

	@Reference
	private JournalArticleLocalService journalArticleLocalService;

	@Reference
	private DLFileEntryLocalService dLFileEntryLocalService;

	@Reference
	private UserLocalService userLocalService;

	@Reference
	private RoleLocalService roleLocalService;

	@Reference
	private EmployeeLocalService employeeLocalServcie;

	@Reference
	private AssetVocabularyLocalService assetVocabularyLocalService;

	private static final Log _log = LogFactoryUtil.getLog(EntityCountPortlet.class);

}
