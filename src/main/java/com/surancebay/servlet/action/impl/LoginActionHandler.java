package com.surancebay.servlet.action.impl;

import com.surancebay.security.LoginManager;
import com.surancebay.servlet.action.ActionHandler;
import com.surancebay.servlet.service.settings.ServletSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Viachaslau Parfenchyk on 22.09.2015.
 */
public class LoginActionHandler implements ActionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginActionHandler.class);

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("LoginActionHandler");

        String inputUsername = req.getParameter(ServletSettings.INPUT_USERNAME_PARAM_NAME);
        String inputPassword = req.getParameter(ServletSettings.INPUT_PASSWORD_PARAM_NAME);
        String errorMessage = (String) req.getAttribute(ServletSettings.MESSAGE_ALERT_PARAM_NAME);
        if (errorMessage==null && inputUsername != null && !inputUsername.isEmpty()) {
            try {
                inputPassword = LoginManager.sha256(inputPassword);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            LOGGER.info("User with email '{}' try to login.", inputUsername);
            req.getSession().setAttribute(ServletSettings.INPUT_USERNAME_PARAM_NAME, inputUsername);
            req.getSession().setAttribute(ServletSettings.INPUT_PASSWORD_PARAM_NAME, inputPassword);
            //check email
            new AuthenticateActionHandler().handle(req, resp);
        } else {

            req.setAttribute(ServletSettings.PAGE_TITLE_PARAM_NAME, ServletSettings.LOGIN_PAGE_TITLE);
            RequestDispatcher dispatcher = req
                    .getRequestDispatcher(ServletSettings.getTemplateName(ServletSettings.LOGIN_PAGE));
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
