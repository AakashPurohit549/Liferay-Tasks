package com.liferay.portlet.mvcportlet.portlet;

import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portlet.configuration.config.EntityConfiguration;
import com.liferay.portlet.constants.EntityCountPortletKeys;

import java.io.IOException;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author ignek
 */
@Component(
	    configurationPid = EntityCountPortletKeys.CONFIGURATION_ID,
	    immediate=true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=EntityCount",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EntityCountPortletKeys.ENTITYCOUNT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EntityCountPortlet extends MVCPortlet {

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {
    

        PortletPreferences prefs = renderRequest.getPreferences();
        
	    renderRequest.setAttribute("entityConfiguration", _entityConfiguration);

        boolean showJournalArticles = Boolean.parseBoolean(prefs.getValue(EntityCountPortletKeys.SHOW_ARTICLES, "false"));
        boolean showDocuments = Boolean.parseBoolean(prefs.getValue(EntityCountPortletKeys.SHOW_DOCUMENTS, "false"));
        boolean showUsers = Boolean.parseBoolean(prefs.getValue(EntityCountPortletKeys.SHOW_USERS, "false"));

        int journalCount = -1;
        int docCount = -1;
        int userCount = -1;
        
        int journalCountInter = -1;
        int docCountInter = -1;
        int userCountInter = -1;


        try {
            if (showJournalArticles) {
                journalCount = journalArticleLocalService.getJournalArticlesCount();
            }
            if (showDocuments) {
                docCount = dLFileEntryLocalService.getDLFileEntriesCount();
            }
            if (showUsers) {
                userCount = userLocalService.getUsersCount();
            }
        } catch (Exception e) {
            _log.error("Error fetching counts", e);
        }
        
        try {

	        if (_entityConfiguration.showJournalArticles()) {
	            journalCountInter = journalArticleLocalService.getJournalArticlesCount();
	        }

	        if (_entityConfiguration.showDocuments()) {
	            docCountInter = dLFileEntryLocalService.getDLFileEntriesCount();
	        }

	        if (_entityConfiguration.showUsers()) {
	            userCountInter = userLocalService.getUsersCount();
	        }

	    } catch (Exception e) {
	        _log.error("Error fetching counts", e);
	    }

        renderRequest.setAttribute("journalCount", journalCount);
        renderRequest.setAttribute("docCount", docCount);
        renderRequest.setAttribute("userCount", userCount);
        
        //for system UI changes 
        renderRequest.setAttribute("journalCountInter", journalCountInter);
        renderRequest.setAttribute("docCountInter", docCountInter);
        renderRequest.setAttribute("userCountInter", userCountInter);
        
        
        renderRequest.setAttribute(EntityCountPortletKeys.SHOW_ARTICLES, showJournalArticles);
        renderRequest.setAttribute(EntityCountPortletKeys.SHOW_DOCUMENTS, showDocuments);
        renderRequest.setAttribute(EntityCountPortletKeys.SHOW_USERS, showUsers);

        super.doView(renderRequest, renderResponse);
    }

    private static final Log _log = LogFactoryUtil.getLog(EntityCountPortlet.class);

    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        _log.info("#####Calling activate() method######");
        
        _entityConfiguration = ConfigurableUtil.createConfigurable(EntityConfiguration.class, properties);
        
    }
    
	
	@Reference
	private JournalArticleLocalService journalArticleLocalService;
	
	@Reference
	private DLFileEntryLocalService dLFileEntryLocalService;
	
	@Reference
	private UserLocalService userLocalService;

    private volatile EntityConfiguration _entityConfiguration;  
}


