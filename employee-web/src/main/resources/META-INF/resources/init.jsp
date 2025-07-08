<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib uri="http://liferay.com/tld/clay" prefix="clay"%>


<%@page import="java.util.List"%>
<%@page import="employee.service.model.Employee"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="employee.service.service.EmployeeLocalService"%>



<liferay-theme:defineObjects />

<portlet:defineObjects />

<%@page import="employee.web.display.context.EmployeeManagementDisplayContext"%>
<%@page import="employee.web.display.context.EmployeeManagementToolbarDisplayContext"%>
<%
EmployeeManagementDisplayContext employeeManagementDisplayContext = EmployeeManagementDisplayContext.create(request,
		liferayPortletRequest, liferayPortletResponse);

%>