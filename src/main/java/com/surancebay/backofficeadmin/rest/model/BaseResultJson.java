package com.surancebay.backofficeadmin.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Viachaslau Parfenchyk on 06.11.2015.
 */
@XmlRootElement
public class BaseResultJson {

    private ResultStatus status;
    private String alertMessage;
    private String infoMessage;

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }

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
}
