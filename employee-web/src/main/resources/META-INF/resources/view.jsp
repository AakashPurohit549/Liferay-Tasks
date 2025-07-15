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

	<div class="modal fade" id="myModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">

					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<span class="title-heading">Are you sure you want to delete
						this employee ?</span>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">No</button>
					<button type="button" class="employee-submit-btn"
						data-dismiss="modal" id="confirmDeleteBtn">Yes</button>

				</div>
			</div>
		</div>
	</div>


	<liferay-ui:search-container
		emptyResultsMessage="No Employees were found"
		searchContainer="<%=employeeManagementDisplayContext.getSearchContainer()%>">


		<liferay-ui:search-container-row
			className="employee.web.dto.EmployeeDTO" modelVar="employeeDTO">



			<liferay-ui:search-container-column-text name="Employee ID"
				cssClass="text-center"
				value="<%=Long.toString(employeeDTO.getEmployeeId())%>" />


			<liferay-ui:search-container-column-text name="First Name"
				orderable="true" orderableProperty="first-name"
				cssClass="text-center" value="<%=employeeDTO.getFirstName()%>" />

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
