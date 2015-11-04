package com.farata.dto;

/**
 * Created by pavlyukevich on 04.11.2015.
 */
public class UserInfoDTO {
    private Long id;
    private Long failedLoginCount;

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
