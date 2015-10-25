package com.farata.dto;

/**
 * Created by andrei on 16.10.2015.
 */
public class UserInfoDTO {

    private long id;

    public UserInfoDTO(long id) {
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public void setfailedLoginCount(long userId){}
}
