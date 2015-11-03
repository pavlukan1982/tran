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
public class JspActionHandler implements ActionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(JspActionHandler.class);

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("JspActionHandler");

        String jspSourceName = (String) req.getAttribute(ServletSettings.JSP_SOURCE_PARAM_NAME);
        jspSourceName = req.getParameter(ServletSettings.JSP_SOURCE_PARAM_NAME);
        LOGGER.debug("req.getContextPath = " + req.getContextPath());
        LOGGER.debug("jspSourceName = " + jspSourceName);
        if (jspSourceName != null) {

            String jspFilename = ServletSettings.getJspFilename(jspSourceName);
            LOGGER.debug("jspFilename = " + jspFilename);
            RequestDispatcher dispatcher = req
                    .getRequestDispatcher(jspFilename);
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
