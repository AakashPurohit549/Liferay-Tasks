<header class="custom_header_style">



<#assign preferences = freeMarkerPortletPreferences.getPreferences({"portletSetupPortletDecoratorId": "barebone", "destination": "/search"}) />
<div id="portlet_com_liferay_portal_search_web_search_bar_portlet_SearchBarPortlet_INSTANCE_templateSearch">
<@liferay.search_bar default_preferences="${preferences}" />

</div>
<#if is_signed_in>
<a href="http://localhost:8080/manage?p_p_id=com_liferay_notifications_web_portlet_NotificationsPortlet&p_p_lifecycle=0&_com_liferay_notifications_web_portlet_NotificationsPortlet_backURL=%2F&p_p_auth=PN0KhXvb">
<i class="bi bi-bell bell-style"></i></a>
</#if>
</header>
