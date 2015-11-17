package com.surancebay.backofficeadmin.rest.model;

/**
 * Created by Viachaslau Parfenchyk on 11.11.2015.
 */
public class UserLookupForm {
    private String producerId;
    private String gaId;
    private String gaName;
    private String gaEmail;
    private String lookupType;

    public String getGaEmail() {
        return gaEmail;
    }

    public void setGaEmail(String gaEmail) {
        this.gaEmail = gaEmail;
    }

    public String getProducerId() {
        return producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    public String getGaId() {
        return gaId;
    }

    public void setGaId(String gaId) {
        this.gaId = gaId;
    }

    public String getGaName() {
        return gaName;
    }

    public void setGaName(String gaName) {
        this.gaName = gaName;
    }

    public String getLookupType() {
        return lookupType;
    }

    public void setLookupType(String lookupType) {
        this.lookupType = lookupType;
    }

    @Override
    public String toString() {
        return "UserLookupForm{" +
                "producerId='" + producerId + '\'' +
                ", gaId='" + gaId + '\'' +
                ", gaName='" + gaName + '\'' +
                ", gaEmail='" + gaEmail + '\'' +
                ", lookupType='" + lookupType + '\'' +
                '}';
    }
}
