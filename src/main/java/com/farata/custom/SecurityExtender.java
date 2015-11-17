package com.farata.custom;

import com.farata.dto.UserInfoDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavlyukevich on 17.11.2015.
 */
public class SecurityExtender {
    public List<UserInfoDTO> getUserInfo(long userId) {
        return new ArrayList<>();
    }

    public void getUserInfo_update(UserInfoDTO user, String ... props){

    }
}
