<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="employee.web.dto.EmployeeDTO" %>

<%@ include file="init.jsp"%>


<%
ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

EmployeeDTO employeeDTO = (EmployeeDTO) row.getObject();
%>


<liferay-ui:icon-menu direction="left-side"
	icon="<%=StringPool.BLANK%>" markupView="lexicon"
	message="<%=StringPool.BLANK%>" showWhenSingleIcon="<%=true%>">
	
	
	
		<portlet:renderURL var="editEmployeeURL"
			copyCurrentRenderParameters="<%=false%>">
			<portlet:param name="jspPage"
				value="/META-INF/resources/update_employee.jsp" />
			<portlet:param name="employeeId"
				value="<%=String.valueOf(employeeDTO.getEmployeeId())%>" />
				
				<portlet:param name="firstName" value="<%= employeeDTO.getFirstName()%>" />
					<portlet:param name="lastName" value="<%= employeeDTO.getLastName()%>" />
					<portlet:param name="phoneNumber" value="<%= employeeDTO.getPhoneNumber()%>" />
					<portlet:param name="emailAddress" value="<%= employeeDTO.getEmailAddress()%>" />
					<portlet:param name="addressLine1" value="<%= employeeDTO.getEmailAddress()%>" />
					<portlet:param name="addressLine2" value="<%= employeeDTO.getCity()%>" />
					<portlet:param name="zipCode" value="<%= employeeDTO.getPhoneNumber()%>" />
					<portlet:param name="city" value="<%= employeeDTO.getCity()%>" />
					<portlet:param name="designation" value="<%= employeeDTO.getDesignation()%>" />
				
		</portlet:renderURL>

		<liferay-ui:icon message="edit" url="<%=editEmployeeURL%>" />
			
				<portlet:actionURL copyCurrentRenderParameters="<%=false%>"
				 name="/deleteEmployee" var="deleteEmployeeURL">
					<portlet:param name="employeeId"
						value="<%=String.valueOf(employeeDTO.getEmployeeId()) %>"></portlet:param>
				</portlet:actionURL> 	
				
    <liferay-ui:icon-delete url="<%=deleteEmployeeURL %>" />
	
	
	</liferay-ui:icon-menu>