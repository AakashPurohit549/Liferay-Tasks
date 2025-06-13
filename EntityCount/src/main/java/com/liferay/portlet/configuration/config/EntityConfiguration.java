package com.liferay.portlet.configuration.config;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import com.liferay.portlet.constants.EntityCountPortletKeys;

import aQute.bnd.annotation.metatype.Meta;



@ExtendedObjectClassDefinition(category = "other",
scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	    id = EntityCountPortletKeys.CONFIGURATION_ID
		)
public interface EntityConfiguration {

    @Meta.AD(deflt = "false", required = false, name = "Show Journal Articles")
     boolean showJournalArticles();

    @Meta.AD(deflt = "false", required = false, name = "Show Documents")
    boolean showDocuments();

    @Meta.AD(deflt = "false", required = false, name = "Show Users")
    boolean showUsers();
}