package com.surancebay.servlet.action.impl;

import com.farata.SecurityServiceDAO;
import com.farata.audit.AuditUserLogin;
import com.farata.dto.UserInfoDTO;
import com.surancebay.security.SecurityContext;
import com.surancebay.servlet.action.ActionHandler;
import com.surancebay.servlet.action.enums.BackOfficeRole;
import com.surancebay.servlet.service.settings.ServletSettings;
import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Viachaslau Parfenchyk on 22.09.2015.
 */
public class AuthenticateActionHandler implements ActionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateActionHandler.class);

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("AuthenticateActionHandler");

        String inputUsername = (String) req.getSession().getAttribute(ServletSettings.INPUT_USERNAME_PARAM_NAME);
        String inputPassword = (String) req.getSession().getAttribute(ServletSettings.INPUT_PASSWORD_PARAM_NAME);

        String alertMessage = null;
        UserInfoDTO userInfo = null;
        List<String> userRoles = null;
        if (inputUsername != null) {
            LOGGER.info(String.format("User with email '%s' logged.", inputUsername));

            try {
                // skipping explicitly here instead of parameter to SecurityFilter in web.xml
                // because of url rewriting
                SecurityContext.setSkipAuthorization(true);
                SecurityServiceDAO securityService = new SecurityServiceDAO();

                List<UserInfoDTO> users = securityService.securityCheck(inputUsername, inputPassword);
                if (users != null) {
                    LOGGER.debug("users.size() = " + users.size());
                    if (users.size() == 1) {
                        userInfo = users.get(0);
                        SecurityContext.setUserId(userInfo.getid());
                        userRoles = SecurityContext.getUserRoles();
                        boolean haveGrantedRole = false;
                        for (String userRole : userRoles) {
                            LOGGER.debug("Available role: " + userRole);
                            haveGrantedRole = EnumUtils.isValidEnum(BackOfficeRole.class, userRole.toUpperCase());
                            if (haveGrantedRole) {
                                LOGGER.debug("{} role granted to user #{}", userRole, userInfo.getid());
                                break;
                            }
                        }
                        if (!haveGrantedRole) {
                            alertMessage = "You have not access!";
                        }
                    } else if (users.size() > 1) {
                        alertMessage = "Founded more than one users with name: " + inputUsername;
                    } else if (users.size() == 0) {
                        alertMessage = "Invalid username or password!";
                    }
                }

            } finally {
                SecurityContext.setSkipAuthorization(false);
            }

            req.setAttribute(ServletSettings.MESSAGE_ALERT_PARAM_NAME, alertMessage);
            if (alertMessage == null) {
                AuditUserLogin.userLogin(userInfo);
                req.setAttribute(ServletSettings.USERNAME_PARAM_NAME, inputUsername);
                new MainPageActionHandler().handle(req, resp);
            } else {
                LOGGER.error(alertMessage);
                new LoginActionHandler().handle(req, resp);
            }

        } else {

            new LoginActionHandler().handle(req, resp);
        }
    }
}
