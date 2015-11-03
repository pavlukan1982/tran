package com.surancebay.servlet.action.impl;

import com.surancebay.servlet.action.ActionHandler;
import com.surancebay.servlet.service.settings.ServletSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Viachaslau Parfenchyk on 22.09.2015.
 */
public class LogoutActionHandler implements ActionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutActionHandler.class);

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("LogoutActionHandler");

        req.getSession().removeAttribute(ServletSettings.INPUT_USERNAME_PARAM_NAME);

        new LoginActionHandler().handle(req, resp);
    }
}
