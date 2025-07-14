package employee.web.actions;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.AddressService;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ContactLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Calendar;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import employee.service.model.Employee;
import employee.service.service.EmployeeLocalService;
import employee.web.constants.EmployeeWebPortletKeys;

@Component(property = { "javax.portlet.name=" + EmployeeWebPortletKeys.EMPLOYEEWEB,
		"mvc.command.name=addEmployee" }, service = MVCActionCommand.class)

public class AddEmployeeMVCAction extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException {

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

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();
		long currentUserId = themeDisplay.getUserId();
		long companyId = themeDisplay.getCompanyId();
		
	try {

		Locale locale = null;

		locale = locale.US;

		boolean autoPassword = false;
		
		boolean autoScreenName = false;

		String screenName = firstName + "_" + lastName;
		String middleName = "";
		long prefixListTypeId = 0;
		long suffixListTypeId = 0;
		boolean male = false;
		Calendar calender = Calendar.getInstance();
		int birthdayMonth = calender.JANUARY;
		int birthdayDay = 1;
		int birthdayYear = 1970;
		String jobTitle = designation;

		long[] groupIds = { groupId };

		long[] organizationIds = null;

		Role userRole = _roleLocalService.getRole(companyId, RoleConstants.USER);
		
		Role employeeRoleId = _roleLocalService.getRole(companyId, "Employee");
		
		employeeRoleId.getRoleId();
		
		System.out.println("Role Id of Employee" + employeeRoleId.getRoleId());
		
		long[] roleIds = { userRole.getRoleId() , employeeRoleId.getRoleId() };

		long[] userGroupIds = null;

		boolean sendEmail = false;
				
		ServiceContext serviceContext = ServiceContextFactory.getInstance(User.class.getName(), actionRequest);

		System.out.println("Company id : " + companyId);
		
		_log.info("Company id : " + companyId);
						
		User user =   userLocalService.addUser(currentUserId,companyId, autoPassword, password, password, 
				autoScreenName, screenName, emailAddress, locale, firstName, middleName, lastName, 
				prefixListTypeId, suffixListTypeId, male, 
				birthdayMonth,birthdayDay, birthdayYear,jobTitle, UserConstants.TYPE_REGULAR, groupIds, organizationIds, roleIds, userGroupIds, sendEmail, serviceContext);
		
		
		userGroupRoleLocalService.addUserGroupRole(currentUserId, groupId, employeeRoleId.getRoleId());
	
	_log.info("Successfuly new user added");
	
	_log.info("User Id of new created user: "+ user.getUserId());
	
		long employeeId = counterLocalService.increment(Employee.class.getName());

		Employee employee = employeeLocalServcice.createEmployee(employeeId);
		
		
		employee.setUserId(currentUserId); //audit filed
		
		employee.setUserName(screenName);
		
		employee.setGroupId(groupId);

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
		
		employeeLocalServcice.addEmployee(employee);
		
		_log.info("Employee created successfully");
		
		
	}catch(Exception e) {
		_log.error("Error in employee creation", e);
	}
	
		

	}

	@Reference
	private CounterLocalService counterLocalService;

	@Reference
	private EmployeeLocalService employeeLocalServcice;

	@Reference
	private UserLocalService userLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private CompanyLocalService _companyLocalService;
	
	@Reference
	private ContactLocalService contactLocalService;

	@Reference
	private RoleLocalService _roleLocalService;
	
	@Reference
	private UserGroupRoleLocalService userGroupRoleLocalService;
	
	@Reference
	private AddressService AddressLocalService;
	
    private static final Log _log = LogFactoryUtil.getLog(AddEmployeeMVCAction.class);


}
