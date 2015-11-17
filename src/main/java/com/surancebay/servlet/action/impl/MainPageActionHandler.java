package com.surancebay.servlet.action.impl;

import com.surancebay.servlet.action.ActionHandler;
import com.surancebay.servlet.datamodel.PanelsData;
import com.surancebay.servlet.service.PanelService;
import com.surancebay.servlet.service.impl.PanelServiceImpl;
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
public class MainPageActionHandler implements ActionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainPageActionHandler.class);
    public static final int PARAMS_LIMIT = 2;

    private static PanelService panelService = PanelServiceImpl.getInstance();

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("MainPageActionHandler");

        LOGGER.debug("Try to read panelsData");
        PanelsData panelsData = panelService.getPanelsData(req.getServletContext().getResourceAsStream(ServletSettings.PANELS_JSON_SOURCE_FILE));
        if (panelsData != null) {
            LOGGER.debug("panelsData: " + panelsData);
            panelsData.setPanelBoxDataList(panelService.filterPanelBoxDataByRoles(panelsData.getPanelBoxDataList()));
            LOGGER.debug("panelsData: " + panelsData);
        } else {
            panelsData = new PanelsData();
        }
        req.setAttribute(ServletSettings.PANELS_DATA_ATTRIBUTE_NAME, panelsData);
        req.setAttribute(ServletSettings.PAGE_TITLE_PARAM_NAME, ServletSettings.MAIN_PAGE_TITLE);
        RequestDispatcher dispatcher = req
                .getRequestDispatcher(ServletSettings.getTemplateName(ServletSettings.MAIN_PAGE));
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
