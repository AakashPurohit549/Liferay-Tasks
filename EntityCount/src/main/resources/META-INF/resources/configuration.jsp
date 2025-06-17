<%@ include file="init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="<%=true%>"
	var="configurationActionURL" />
<liferay-portlet:renderURL portletConfiguration="<%=true%>"
	var="configurationRenderURL" />

<aui:form action="${configurationActionURL}" method="post" name="fm">
	<aui:input name="<%=Constants.CMD%>" type="hidden"
		value="<%=Constants.UPDATE%>" />
	<aui:input name="redirect" type="hidden"
		value="${configurationRenderURL}" />

	<aui:fieldset>
		<aui:select name="displayType" label="Select content to display">
			<aui:option label="Show Users" value="Users"
				selected="<%=showUsers%>"></aui:option>
			<aui:option label="Show Images" value="Images"
				selected="<%=showImages%>"></aui:option>
			<aui:option label="Show Journal Articles" value="Artilces"
				selected="<%=showJournalArticles%>"></aui:option>
			<aui:option label="Show Employees" value="Employees"
				selected="<%=showEmployees%>"></aui:option>
			<aui:option label="Show Categories" value="Categories"
				selected="<%=showCategories%>"></aui:option>

		</aui:select>
	</aui:fieldset>

	<aui:button type="submit" value="Save" />
</aui:form>

