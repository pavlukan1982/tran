package com.surancebay.servlet.service.settings;

import com.surancebay.servlet.action.enums.Action;

/**
 * Created by Viachaslau Parfenchyk on 29.09.2015.
 */
public class ServletSettings {
    public static final String SERVLET_DOMAIN = "/sbweb";
    public static final String MAIN_SERVLET_PATH = SERVLET_DOMAIN + "/BackOfficeAdmin";
    public static final String JSP_PAGE_PREFIX = "/admin";
    public static final String TEMPLATE_PAGE_PREFIX = "/backofficeadmin";
    public static final String TEMPLATE_PAGE_SUFFIX = ".jsp";

    public static final String USERNAME_PARAM_NAME = "username";
    public static final String MESSAGE_ALERT_PARAM_NAME = "alertMessage";
    public static final String MESSAGE_INFO_PARAM_NAME = "infoMessage";
    public static final String INPUT_USERNAME_PARAM_NAME = "emailValue";
    public static final String INPUT_PASSWORD_PARAM_NAME = "passwordValue";

    public static final String SERVICE_URL_PARAM_NAME = "serviceUrl";

    public static final String UNLOCK_ACCOUNT_ACTION_NAME = "unlockAccountAction";
    public static final String RESET_PASSWORD_ACTION_NAME = "resetPasswordActionAction";

    public static final String LOGIN_PAGE = "loginPanel";
    public static final String MAIN_PAGE = "mainPanel";

    public static final String LOGIN_PAGE_TITLE = "Login";
    public static final String MAIN_PAGE_TITLE = "Back Office Admin";

    public static final String USER_LOOKUP_PAGE = "userLookup";

    public static final String ACTION_PARAM_NAME = "action";
    public static final String PAGE_TITLE_PARAM_NAME = "pageTitle";


    public static final String PANELS_JSON_SOURCE_FILE = TEMPLATE_PAGE_PREFIX + "/panels.json";
    public static final String PANELS_DATA_ATTRIBUTE_NAME = "panelsData";

    public static final String JSP_SOURCE_PARAM_NAME = "source";

    public static String getJspFilename(String jspPageName) {
        return JSP_PAGE_PREFIX + "/" + jspPageName + TEMPLATE_PAGE_SUFFIX;
    }

    public static String getJspSourceUrl(String jspPageName) {
        return getActionUrl(Action.JSP) + "&" + JSP_SOURCE_PARAM_NAME + "=" + jspPageName;
    }

    public static String getTemplateName(String pagename) {
        return TEMPLATE_PAGE_PREFIX + "/" + pagename + TEMPLATE_PAGE_SUFFIX;
    }

    private static String getActionUrl(Action action, String servletPath) {
        return servletPath + "?" + ACTION_PARAM_NAME + "=" + action.toString().toLowerCase();
    }

    public static String getActionUrl(Action action) {
        return getActionUrl(action, MAIN_SERVLET_PATH);
    }

}
