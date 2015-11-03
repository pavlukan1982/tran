package com.surancebay.servlet.datamodel;

import com.farata.dto.UserInfoDTO;

/**
 * Created by Viachaslau Parfenchyk on 27.10.2015.
 * This class contains the answer for the Ajax service request from BackOfficeAdmin.
 */
public class AjaxServiceResult {

    private String alertMessage;
    private String infoMessage;
    private UserInfoDTO userInfo;

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public UserInfoDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoDTO userInfo) {
        this.userInfo = userInfo;
    }
}
