package com.farata.dao;

import com.farata.dto.UserInfoDTO;

import java.util.List;

/**
 * Created by pavlyukevich on 17.11.2015.
 */
public class DaoServices {
    public static UserInfoDTO firstOrNull(List<UserInfoDTO> lst) {
        return lst.get(0);
    }
}
