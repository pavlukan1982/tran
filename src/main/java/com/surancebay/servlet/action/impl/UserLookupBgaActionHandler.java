package com.surancebay.servlet.action.impl;

import com.surancebay.servlet.action.ActionHandler;
import com.surancebay.servlet.service.settings.ServletSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Viachaslau Parfenchyk on 22.09.2015.
 */
public class UserLookupBgaActionHandler implements ActionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLookupBgaActionHandler.class);

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("UserLookupBgaActionHandler");

        req.setAttribute(ServletSettings.SERVICE_URL_PARAM_NAME, ServletSettings.REST_LOOKUP_SERVICE_PATH);
        req.setAttribute(ServletSettings.UNLOCK_ACCOUNT_SERVICE_NAME, ServletSettings.REST_UNLOCK_ACCOUNT_SERVICE_PATH);

        RequestDispatcher dispatcher = req
                .getRequestDispatcher(ServletSettings.getTemplateName(ServletSettings.USER_LOOKUP_BGA_PAGE));
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
