<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@page import="com.liferay.portlet.constants.EntityCountPortletKeys"%>
<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
PortletPreferences prefs = renderRequest.getPreferences();
boolean showJournalArticles = Boolean.parseBoolean(prefs.getValue(EntityCountPortletKeys.SHOW_ARTICLES, "false"));
boolean showDocuments = Boolean.parseBoolean(prefs.getValue(EntityCountPortletKeys.SHOW_DOCUMENTS, "false"));
boolean showUsers = Boolean.parseBoolean(prefs.getValue(EntityCountPortletKeys.SHOW_USERS, "false"));
%>