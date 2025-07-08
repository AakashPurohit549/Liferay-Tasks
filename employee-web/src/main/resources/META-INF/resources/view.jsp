<%@ include file="init.jsp"%>


<%
EmployeeManagementToolbarDisplayContext employeeManagementToolbarDisplayContext = new EmployeeManagementToolbarDisplayContext(
		request, liferayPortletRequest, liferayPortletResponse, employeeManagementDisplayContext);
%>

<aui:form name="searchfm1" method="get"
	action="<%=employeeManagementToolbarDisplayContext.getSearchActionURL()%>">
	<clay:management-toolbar
		managementToolbarDisplayContext="<%=employeeManagementToolbarDisplayContext%>" />
</aui:form>



<div class="container-fluid container-fluid-max-xl container-view">




	<liferay-ui:search-container
		emptyResultsMessage="No Employees were found"
		searchContainer="<%=employeeManagementDisplayContext.getSearchContainer()%>">


		<liferay-ui:search-container-row
			className="employee.web.dto.EmployeeDTO" modelVar="employeeDTO">


			<liferay-ui:search-container-column-text name="First Name"
				orderable="true" orderableProperty="first-name"
				cssClass="text-center" value="<%=employeeDTO.getFirstName()%>" />


			<liferay-ui:search-container-column-text name="Employee ID"
				cssClass="text-center"
				value="<%=Long.toString(employeeDTO.getEmployeeId())%>" />


			<liferay-ui:search-container-column-text name="Last Name"
				cssClass="text-center" value="<%=employeeDTO.getLastName()%>" />

			<liferay-ui:search-container-column-text name="Designation"
				orderable="true" orderableProperty="designation"
				cssClass="text-center" value="<%=employeeDTO.getDesignation()%>" />

			<liferay-ui:search-container-column-text name="EmailAddress"
				cssClass="text-center" value="<%=employeeDTO.getEmailAddress()%>" />

			<liferay-ui:search-container-column-text name="Phone Number"
				cssClass="text-center" value="<%=employeeDTO.getPhoneNumber()%>" />

			<liferay-ui:search-container-column-text name="City"
				cssClass="text-center" value="<%=employeeDTO.getCity()%>" />

			<liferay-ui:search-container-column-jsp cssClass="text-center"
				path="/actions.jsp" />

		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator displayStyle="list" markupView="lexicon" />

	</liferay-ui:search-container>

</div>


<!--  delta="1" deltaConfigurable="true"  -->

<%-- 	searchContainer="<%=searchContainer %>" --%>
