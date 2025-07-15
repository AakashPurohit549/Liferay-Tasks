package employee.web.display.context;

import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Reference;

import employee.service.model.Employee;
import employee.service.model.EmployeeTable;
import employee.service.service.EmployeeLocalServiceUtil;
import employee.web.constants.EmployeeWebPortletKeys;
import employee.web.dto.EmployeeDTO;

public class EmployeeManagementDisplayContext {
	
	
	
	private final ThemeDisplay _themeDisplay;
	private final HttpServletRequest _httpServletRequest;
	private final LiferayPortletRequest _liferayPortletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private SearchContainer<?> _searchContainer;
	private SearchContainer<EmployeeDTO> _employeeSearchContainer;
	private String _orderByType; 
	private String _keywords;
	private String _navigation;
	private String _orderBy;
	private Employee employee;
	

	@Reference
	private EmployeeLocalServiceUtil employeeLocalServiceutil;
	

	private static final Log log = LogFactoryUtil.getLog(EmployeeManagementDisplayContext.class); 



	public static EmployeeManagementDisplayContext create(HttpServletRequest httpServletRequest,
			LiferayPortletRequest liferayPortletRequest, LiferayPortletResponse liferayPortletResponse) {
		
		EmployeeManagementDisplayContext employeeManagementDisplayContext = (EmployeeManagementDisplayContext) liferayPortletRequest
				.getAttribute(EmployeeWebPortletKeys.EMPLOYEEWEB);
		
		if (employeeManagementDisplayContext == null) {
			employeeManagementDisplayContext = new EmployeeManagementDisplayContext(httpServletRequest,
					liferayPortletRequest, liferayPortletResponse);

			liferayPortletRequest.setAttribute(EmployeeWebPortletKeys.EMPLOYEEWEB,
					employeeManagementDisplayContext);
		}
		
		return employeeManagementDisplayContext;
}
	
	public EmployeeManagementDisplayContext(HttpServletRequest httpServletRequest,
			LiferayPortletRequest liferayPortletRequest, LiferayPortletResponse liferayPortletResponse) {
		_httpServletRequest = httpServletRequest;
		_liferayPortletRequest = liferayPortletRequest;
		_liferayPortletResponse = liferayPortletResponse;
		_themeDisplay = (ThemeDisplay) _httpServletRequest.getAttribute(WebKeys.THEME_DISPLAY);
	
	}
  
	
	
  public SearchContainer<EmployeeDTO> getSearchContainer() throws PortalException {

		if (_employeeSearchContainer != null) {
			return _employeeSearchContainer;
		}
		

		SearchContainer<EmployeeDTO> employeeSearchContainer = new SearchContainer<>(_liferayPortletRequest,
				getPortletURL(), null, null);
	
		employeeSearchContainer.setOrderByCol(getOrderBy());
		employeeSearchContainer.setOrderByType(getOrderByType());	
		
		List<Employee> employeeList = new ArrayList<>(employeeLocalServiceutil.getEmployees(0,5));
	
		//searching
		String keywords = getKeywords();
		if (Validator.isNotNull(keywords))
		{
					  DSLQuery query = DSLQueryFactoryUtil
					    .select()
					    .from(EmployeeTable.INSTANCE)
					    .where(EmployeeTable.INSTANCE.firstName.like(keywords));
		
					  employeeList = employeeLocalServiceutil.dslQuery(query);
		}
				
		//filtering				
		String filterBy = getNavigation(); 
		if (filterBy != null && filterBy.contains(":")) {
		    System.out.println(filterBy);
		    String[] parts = filterBy.split(":");
		    if (parts.length == 2) {
		        String field = parts[0];
		        String value = parts[1];

		        switch (field) {
		            case "designation-type":
		                employeeList = employeeList.stream()
		                    .filter(emp -> value.equalsIgnoreCase(emp.getDesignation()))
		                    .collect(Collectors.toList());
		                break;
		            case "city-type":
		                employeeList = employeeList.stream()
		                    .filter(emp -> value.equalsIgnoreCase(emp.getCity()))
		                    .collect(Collectors.toList());
		                break;
		        }
		    }
		}

			
//				Comparator<Employee> comparator = Comparator.comparing(Employee::getFirstName, String.CASE_INSENSITIVE_ORDER);
//				switch (getOrderBy()) {
//					case "designation" -> comparator = Comparator.comparing(Employee::getDesignation, String.CASE_INSENSITIVE_ORDER);
//					case "first-name" -> comparator = Comparator.comparing(Employee::getFirstName, String.CASE_INSENSITIVE_ORDER);
//				}
//				if ("desc".equalsIgnoreCase(getOrderByType())) {
//					comparator = comparator.reversed();
//				}
//				employeeList.sort(comparator);

				
		
				List<EmployeeDTO> dtoList = employeeList.stream().map(emp -> {
					EmployeeDTO dto = new EmployeeDTO();
					dto.setEmployeeId(emp.getEmployeeId());
					dto.setFirstName(emp.getFirstName());
					dto.setLastName(emp.getLastName());
					dto.setDesignation(emp.getDesignation());
					dto.setEmailAddress(emp.getEmailAddress());
					dto.setCity(emp.getCity());
					dto.setPhoneNumber(emp.getPhoneNumber());
					return dto;
				}).collect(Collectors.toList());
				
				employeeSearchContainer.setResultsAndTotal(dtoList);
				_employeeSearchContainer = employeeSearchContainer;
				return _employeeSearchContainer;
				
  }
					

	public PortletURL getPortletURL() {
		PortletURL portletURL = _liferayPortletResponse.createRenderURL();
		
		String keywords = ParamUtil.getString(_httpServletRequest, "keywords");
		if (Validator.isNotNull(keywords)) {
			portletURL.getRenderParameters().setValue("keywords", keywords);
			System.out.println(keywords);
		}


		String cur = ParamUtil.getString(_httpServletRequest, "cur");
		if (Validator.isNotNull(cur)) {
			portletURL.getRenderParameters().setValue("cur", cur);
		}

		String filterByType = ParamUtil.getString(_httpServletRequest, "filterByType");
		if (Validator.isNotNull(filterByType)) {
			portletURL.getRenderParameters().setValue("filterByType", filterByType);
		}
		
		String navigation = ParamUtil.getString(_httpServletRequest, "navigation");
		if (Validator.isNotNull(filterByType)) {
			portletURL.getRenderParameters().setValue("navigation", navigation);
		}

		String delta = ParamUtil.getString(_httpServletRequest, "delta");
		if (Validator.isNotNull(delta)) {
			portletURL.getRenderParameters().setValue("delta", delta);
		}

		String resetCur = ParamUtil.getString(_httpServletRequest, "resetCur");
		if (Validator.isNotNull(resetCur)) {
			portletURL.getRenderParameters().setValue("resetCur", resetCur);
		}

		String orderBy = ParamUtil.getString(_httpServletRequest, "orderBy");
		if (Validator.isNotNull(orderBy)) {
			portletURL.getRenderParameters().setValue("orderBy", orderBy);
		}
		
		return portletURL;
	}
	
	
	
	
	public String getOrderByType() {
		_orderByType = ParamUtil.getString(_httpServletRequest, getOredrByTypeParam(), "desc");
		return _orderByType;
	}
	
	
	
	public String getOrderBy() {
	    _orderBy = ParamUtil.getString(_httpServletRequest, getOrderByParam(), "firstName");
	    return _orderBy;
	}

	
	public boolean isSearch() {
		if (Validator.isNotNull(getKeywords())) {
			return true;
		}

		return false;
	}

	public String getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(_httpServletRequest, "keywords");
		return _keywords;
	}
	
	
	public String getFilterByTypeParam() {
		return "filterByType";
	}
	
	public String getOredrByTypeParam() {
		return "orderByType";
	}
	
	public String getOrderByParam() {
		return "orderBy";
	}
	
	
	public String getNavigation() {
		if (_navigation != null) {
			return _navigation;
		}

		_navigation = ParamUtil.getString(_httpServletRequest, "navigation");
		return _navigation;
	}	
	
}