package com.farata.dto;

/**
 * Created by andrei on 16.10.2015.
 */
public class UserLookupDTO {

    private long id;

    public UserLookupDTO(long id) {
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public void setfailedLoginCount(long userId){}
}
