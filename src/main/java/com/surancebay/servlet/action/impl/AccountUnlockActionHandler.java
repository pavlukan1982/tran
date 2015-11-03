package com.surancebay.servlet.action.impl;

import com.farata.SecurityServiceDAO;
import com.farata.dto.UserInfoDTO;
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
import java.util.List;

/**
 * Created by Viachaslau Parfenchyk on 22.09.2015.
 */
public class AccountUnlockActionHandler implements ActionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountUnlockActionHandler.class);

    private static AjaxService ajaxService = AjaxServiceImpl.getInstance();

    public static final String INPUT_USER_ID_PARAM_NAME = "userId";

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("AccountUnlockActionHandler");

        String alertMessage = null;
        String infoMessage = null;
        String userIdString = req.getParameter(INPUT_USER_ID_PARAM_NAME);
        if (userIdString != null) {
            LOGGER.debug("userIdString = {}", userIdString);
            long userId = Long.valueOf(userIdString);
            if (userId > 0) {
                SecurityServiceDAO securityService = new SecurityServiceDAO();
                List<UserInfoDTO> userInfoList = securityService.getUserInfo(userId);
                if (userInfoList != null && userInfoList.size() > 0) {
                    UserInfoDTO userInfoDTO = userInfoList.get(0);
                    userInfoDTO.setfailedLoginCount(0L);
                    securityService.securityCheck_update(userInfoDTO, "failedLoginCount");
                    infoMessage = String.format("A user account #%s successfully unlocked.",userId);
                } else {
                    alertMessage = String.format("Can't find userinfo by id = {}", userId);
                }
            }
        } else {
            alertMessage = "Invalid parameters!";
        }

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
