package employee.web.actions;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import employee.service.model.Employee;
import employee.service.service.EmployeeLocalService;
import employee.web.constants.EmployeeWebPortletKeys;

@Component(property = { "javax.portlet.name=" + EmployeeWebPortletKeys.EMPLOYEEWEB,
		"mvc.command.name=/deleteEmployee" }, service = MVCActionCommand.class)
public class DeleteEmployeeMVCAction extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) {
		long employeeId = ParamUtil.getLong(actionRequest, "employeeId", GetterUtil.DEFAULT_LONG);

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();

		try {

			// first get the userId related to the employee
			// As user audit feild is used here
			Employee employee = employeeLocalServcice.getEmployee(employeeId);

			// email is mapped to the user and employee
			String userEmail = employee.getEmailAddress();

			System.out.println("Email : " + userEmail);

			User user = userLocalService.getUserByEmailAddress(companyId, userEmail);

			System.out.println("Employee Deleted : " + user);

			userLocalService.deleteUser(user);

			employeeLocalServcice.deleteEmployee(employeeId);

			System.out.println("---########## Employee and User deleted ##########" + employee);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Reference
	private EmployeeLocalService employeeLocalServcice;

	@Reference
	private UserLocalService userLocalService;
}
