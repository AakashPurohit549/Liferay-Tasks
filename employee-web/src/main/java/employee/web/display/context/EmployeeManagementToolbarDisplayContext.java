package employee.web.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItemListBuilder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;

public class EmployeeManagementToolbarDisplayContext extends SearchContainerManagementToolbarDisplayContext {

	public EmployeeManagementToolbarDisplayContext(HttpServletRequest httpServletRequest,
			LiferayPortletRequest liferayPortletRequest, LiferayPortletResponse liferayPortletResponse,
			EmployeeManagementDisplayContext employeeManagementDisplayContext) throws PortalException {

		super(httpServletRequest, liferayPortletRequest, liferayPortletResponse,
				employeeManagementDisplayContext.getSearchContainer());

		_employeeManagementDisplayContext = employeeManagementDisplayContext;

		_themeDisplay = (ThemeDisplay) httpServletRequest.getAttribute(WebKeys.THEME_DISPLAY);

	}



	@Override
	public Boolean isSelectable() {
		return true;
	}
	
	@Override
	protected String[] getNavigationKeys() {
		return new String[]{"all", "designation-type:Liferay Developer", "city-type:Banglore"};
	}

	@Override
	protected String[] getOrderByKeys() {
		return new String[]{"first-name", "designation"};
	}
	


	@Override
	public String getClearResultsURL() {
		return PortletURLBuilder.create(getPortletURL()).setKeywords(StringPool.BLANK)
				.setParameter(_employeeManagementDisplayContext.getFilterByTypeParam()).buildString();
	}

	@Override
	public String getComponentId() {
		return "employeeManagementToolbar";
	}

	@Override
	public Boolean isShowSearch() {
		return true;
	}

	@Override
	public CreationMenu getCreationMenu() {
		try {
			return _getCreationMenu();
		} catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to get creation menu", portalException);
			}
		}
		return null;
	}

	
	
	@Override
	public String getSearchActionURL() {
		PortletURL searchActionURL = getPortletURL();
		return searchActionURL.toString();

	}

	@Override
	public String getSearchContainerId() {
		return "employees";
	}

	@Override
	public String getSearchFormName() {
		return "searchfm1";
	}
	
	@Override
	public Boolean isShowFiltersDoneButton() {
		return true;
	}



	private CreationMenu _getCreationMenu() throws PortalException {
		return new CreationMenu() {
			{
				if (true) {

					addPrimaryDropdownItem(dropdownItem -> {
						dropdownItem.setHref(liferayPortletResponse.createRenderURL(), "mvcPath", "/add_employee.jsp",
								"redirect", PortalUtil.getCurrentURL(httpServletRequest), "groupId",
								String.valueOf(_themeDisplay.getScopeGroupId()));

						String label = "Lets see";

						dropdownItem.setLabel(LanguageUtil.get(httpServletRequest, label));
					});
				}

				setHelpText(LanguageUtil.get(httpServletRequest,
						"you-can-customize-this-menu-or-see-all-you-have-by-" + "clicking-more"));
			}
		};
	}
			
	

	private static final Log _log = LogFactoryUtil.getLog(EmployeeManagementToolbarDisplayContext.class);

	private final EmployeeManagementDisplayContext _employeeManagementDisplayContext;

	private final ThemeDisplay _themeDisplay;

}
