package com.surancebay.servlet.action.impl;

import com.surancebay.servlet.action.ActionHandler;
import com.surancebay.servlet.datamodel.AjaxServiceResult;
import com.surancebay.servlet.service.AjaxService;
import com.surancebay.servlet.service.impl.AjaxServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Viachaslau Parfenchyk on 22.09.2015.
 */
public class PasswordResetActionHandler implements ActionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordResetActionHandler.class);

    private static AjaxService ajaxService = AjaxServiceImpl.getInstance();

    public static final String INPUT_USER_ID_PARAM_NAME = "userId";

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("PasswordResetActionHandler");

        String alertMessage = null;
        String infoMessage = null;

        infoMessage = "Reset password service is under construction";

        AjaxServiceResult ajaxServiceResult = new AjaxServiceResult();
        ajaxServiceResult.setAlertMessage(alertMessage);
        ajaxServiceResult.setInfoMessage(infoMessage);

        try {
            resp.setContentType("application/json");
            PrintWriter out  = resp.getWriter();
            out.print(ajaxService.buildJsonString(ajaxServiceResult));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
