package employee.web.actions;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import employee.service.model.Employee;
import employee.service.service.EmployeeLocalService;
import employee.web.constants.EmployeeWebPortletKeys;

@Component(property = { "javax.portlet.name=" + EmployeeWebPortletKeys.EMPLOYEEWEB,
		"mvc.command.name=/updateEmployee" }, service = MVCActionCommand.class)
public class UpdateEmployeeMVCAction extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionresponse) {

		//implement exception here . 
		
		
		
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		
		
		long employeeId = ParamUtil.getLong(actionRequest, "employeeId", GetterUtil.DEFAULT_LONG);
		if(employeeId <= 0) {
			System.out.println("EmplyeeId paramater not passed correctly!");
		}

		String firstName = ParamUtil.getString(actionRequest, "firstName");
		String lastName = ParamUtil.getString(actionRequest, "lastName");
		String emailAddress = ParamUtil.getString(actionRequest, "emailAddress");
		String password = ParamUtil.getString(actionRequest, "password");
		String phoneNumber = ParamUtil.getString(actionRequest, "phoneNumber");
		String addressLine1 = ParamUtil.getString(actionRequest, "addressLine1");
		String addressLine2 = ParamUtil.getString(actionRequest, "addressLine2");
		String city = ParamUtil.getString(actionRequest, "city");
		String zipCode = ParamUtil.getString(actionRequest, "zipCode");
		String designation = ParamUtil.getString(actionRequest, "designation");

		Employee employee = null;
		try {
			employee = employeeLocalServcice.getEmployee(employeeId);
		} catch (Exception e) {
			System.out.println(e);
		}

		if (Validator.isNotNull(employee)) {

			employee.setFirstName(firstName);

			employee.setLastName(lastName);

			employee.setPassword(password);

			employee.setEmailAddress(emailAddress);

			employee.setPhoneNumber(phoneNumber);

			employee.setAddressLine1(addressLine1);

			employee.setAddressLine2(addressLine2);

			employee.setCity(city);

			employee.setZipCode(zipCode);

			employee.setDesignation(designation);
			
			
			employeeLocalServcice.updateEmployee(employee);

			
			
			//User user = userLocalService.fetchUserByEmailAddress(companyId, emailAddress);
			
//			if(user != null) {
//				employeeLocalServcice.updateEmployee(employee);
//			}
//			else {
//				System.out.println("User with Email already exists, enter a different email");
//			}


		}

	}

	@Reference
	private EmployeeLocalService employeeLocalServcice;
	
	@Reference
	private UserLocalService userLocalService;
}
