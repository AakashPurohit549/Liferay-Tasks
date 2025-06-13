<%@ include file="init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%=true%>" var="configurationActionURL" />
<liferay-portlet:renderURL portletConfiguration="<%=true%>" var="configurationRenderURL" />

<aui:form action="${configurationActionURL}" method="post" name="fm">
    <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
    <aui:input name="redirect" type="hidden" value="${configurationRenderURL}" />

    <aui:input type="checkbox" name="showJournalArticles" label="Show Journal Articles"
        checked="<%= showJournalArticles %>" />

    <aui:input type="checkbox" name="showDocuments" label="Show Documents"
        checked="<%= showDocuments %>" />

    <aui:input type="checkbox" name="showUsers" label="Show Users"
        checked="<%= showUsers %>" />

    <aui:button type="submit" value="Save" />
</aui:form>

