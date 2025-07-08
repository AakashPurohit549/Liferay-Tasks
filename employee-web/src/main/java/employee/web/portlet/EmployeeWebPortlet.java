package employee.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import employee.web.constants.EmployeeWebPortletKeys;

/**
 * @author ignek
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=EmployeeWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EmployeeWebPortletKeys.EMPLOYEEWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class EmployeeWebPortlet extends MVCPortlet {
	
	
//	@Reference
//	private EmployeeLocalService employeeLocalService;
	
//
//	@Override
//	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException{
//
//	
////		List<Employee> employeeList = employeeLocalService.getEmployees(QueryUtil.ALL_POS,QueryUtil.ALL_POS);
////		renderRequest.setAttribute("employees", employeeList);
////		System.out.println(employeeList);
//		super.render(renderRequest, renderResponse);
//	}

	
}