package com.surancebay.servlet;


import com.surancebay.servlet.action.ActionHandler;
import com.surancebay.servlet.action.enums.Action;
import com.surancebay.servlet.action.impl.*;
import com.surancebay.servlet.service.settings.ServletSettings;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Viachaslau Parfenchyk on 22.09.2015.
 */
public class BackOfficeAdminServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(BackOfficeAdminServlet.class);

    private Map<Action, ActionHandler> actionsMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        actionsMap.put(Action.LOGIN, new LoginActionHandler());
        actionsMap.put(Action.LOGOUT, new LogoutActionHandler());
        actionsMap.put(Action.SHOW_CONTROL_PANEL, new MainPageActionHandler());
        actionsMap.put(Action.JSP, new JspActionHandler());
        actionsMap.put(Action.USER_LOOKUP, new UserLookupActionHandler());
        actionsMap.put(Action.PASSWORD_RESET, new PasswordResetActionHandler());
        actionsMap.put(Action.ACCOUNT_UNLOCK, new AccountUnlockActionHandler());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("doGet");
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("doPost");
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String actionString = req.getParameter(ServletSettings.ACTION_PARAM_NAME);
        if (actionString == null) {
            actionString = Action.SHOW_CONTROL_PANEL.toString();
        }
        LOGGER.debug("actionString = " + actionString);
        Action action = Action.valueOf(actionString.toUpperCase());

        // check user
        String inputEmail = (String) req.getSession().getAttribute(ServletSettings.INPUT_USERNAME_PARAM_NAME);
        if (inputEmail == null) {
            action = Action.LOGIN;
        } else {
            req.setAttribute(ServletSettings.USERNAME_PARAM_NAME,inputEmail);
        }
        LOGGER.debug("action: "+action);

        actionsMap.get(action).handle(req, resp);
    }
}
