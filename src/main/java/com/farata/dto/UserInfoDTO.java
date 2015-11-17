package com.farata.dto;

import java.util.Date;

/**
 * Created by pavlyukevich on 04.11.2015.
 */
public class UserInfoDTO {
    private Long id;
    private Long failedLoginCount;
    public Date passwordChangedOn;

    public void setfailedLoginCount(Long setfailedLoginCount) {
        this.failedLoginCount = setfailedLoginCount;
    }

    public UserInfoDTO(Long id) {
        this.id = id;
    }

    public Long getid() {
        return id;
    }
}
