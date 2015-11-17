package com.farata.dto;

import java.util.Date;

/**
 * Created by andrei on 16.10.2015.
 */
public class UserLookupDTO {

    private long id;
    private long userId;
    private long failedLoginCount;
    private Date passwordChangedOn;
    private Date lastLoginAttempt;
    private String firstName;
    private long gaId;
    private String gaName;

    public void setId(long id) {
        this.id = id;
    }

    public long getuserId() {
        return userId;
    }

    public void setuserId(long userId) {
        this.userId = userId;
    }

    public long getfailedLoginCount() {
        return failedLoginCount;
    }

    public Date getpasswordChangedOn() {
        return passwordChangedOn;
    }

    public void setpasswordChangedOn(Date passwordChangedOn) {
        this.passwordChangedOn = passwordChangedOn;
    }

    public Date getlastLoginAttempt() {
        return lastLoginAttempt;
    }

    public void setlastLoginAttempt(Date lastLoginAttempt) {
        this.lastLoginAttempt = lastLoginAttempt;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getgaId() {
        return gaId;
    }

    public void setgaId(long gaId) {
        this.gaId = gaId;
    }

    public String getgaName() {
        return gaName;
    }

    public void setgaName(String gaName) {
        this.gaName = gaName;
    }

    public long getId(){
        return id;
    }

    public void setfailedLoginCount(long userId){}
}
