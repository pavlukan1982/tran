package com.surancebay.servlet.action.impl;

import com.farata.SecurityServiceDAO;
import com.farata.dto.UserLookupDTO;
import com.surancebay.servlet.action.ActionHandler;
import com.surancebay.servlet.action.enums.Action;
import com.surancebay.servlet.service.settings.ServletSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Viachaslau Parfenchyk on 22.09.2015.
 */
public class UserLookupActionHandler implements ActionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLookupActionHandler.class);

    public static final String INPUT_PRODUCER_ID_PARAM_NAME = "producerId";
    public static final String INPUT_GA_ID_PARAM_NAME = "gaId";
    public static final String INPUT_GA_NAME_PARAM_NAME = "gaName";
    public static final String USER_INFO_LIST_PARAM_NAME = "userInfoList";

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("UserLookupActionHandler");

        String alertMessage = null;
        String producerIdString = req.getParameter(INPUT_PRODUCER_ID_PARAM_NAME);
        if (producerIdString != null) {
            String gaIdString = req.getParameter(INPUT_GA_ID_PARAM_NAME);
            String gaName = req.getParameter(INPUT_GA_NAME_PARAM_NAME);
            LOGGER.debug(String.format("gaIdString = `%s`", gaIdString));
            if (gaIdString != null || !gaIdString.isEmpty() || gaName != null || !gaName.isEmpty()) {
                gaIdString = (gaIdString == null || gaIdString.length()==0)?"0":gaIdString;
                long producerId = 0;
                long gaId = 0;
                try {
                    producerId = Long.valueOf(producerIdString);
                } catch (NumberFormatException e) {
                    alertMessage = "Invalid value given for parameter ProducerId!";
                }
                try {
                    gaId = Long.valueOf(gaIdString);
                } catch (NumberFormatException e) {

                }


                if (producerId > 0) {
                    SecurityServiceDAO securityService = new SecurityServiceDAO();
                    List<UserLookupDTO> userInfoList = securityService.getUserByProducerIdAndBga(producerId, gaId, gaName);
                    LOGGER.debug("userInfoList.size() = " + userInfoList.size());
                    if (userInfoList.size() > 0) {
                        req.setAttribute(USER_INFO_LIST_PARAM_NAME, userInfoList);
                    } else {
                        alertMessage = "Can't find any user by given criteria!";
                    }
                    req.setAttribute(INPUT_PRODUCER_ID_PARAM_NAME, producerId);
                    if (gaId > 0) {
                        req.setAttribute(INPUT_GA_ID_PARAM_NAME, gaId);
                    } else {
                        req.removeAttribute(INPUT_GA_ID_PARAM_NAME);
                    }
                    req.setAttribute(INPUT_GA_NAME_PARAM_NAME, gaName);
                }
            } else {
                alertMessage = "Please enter value for " + INPUT_GA_ID_PARAM_NAME+ " or " + INPUT_GA_NAME_PARAM_NAME;
            }
            req.setAttribute(INPUT_PRODUCER_ID_PARAM_NAME, producerIdString);
        }

        req.setAttribute(ServletSettings.MESSAGE_ALERT_PARAM_NAME, alertMessage);
        req.setAttribute(ServletSettings.UNLOCK_ACCOUNT_ACTION_NAME, Action.ACCOUNT_UNLOCK);
        req.setAttribute(ServletSettings.RESET_PASSWORD_ACTION_NAME, Action.PASSWORD_RESET);
        req.setAttribute(ServletSettings.ACTION_PARAM_NAME, Action.USER_LOOKUP);
        req.setAttribute(ServletSettings.SERVICE_URL_PARAM_NAME, ServletSettings.MAIN_SERVLET_PATH);
        RequestDispatcher dispatcher = req
                .getRequestDispatcher(ServletSettings.getTemplateName(ServletSettings.USER_LOOKUP_PAGE));
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
