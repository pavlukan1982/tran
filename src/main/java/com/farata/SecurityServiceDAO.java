package com.farata;

import com.farata.dto.UserInfoDTO;
import com.farata.dto.UserLookupDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrei on 16.10.2015.
 */
public class SecurityServiceDAO {
    public List<UserLookupDTO> getUserByProducerIdAndBga(long producerId, long bgaId, String bgaName){
        return new ArrayList<>();
    }

    public List<UserInfoDTO> getUserInfo(long userId){

        List<UserInfoDTO> lst = new ArrayList<UserInfoDTO>();
        lst.add(new UserInfoDTO(10L));
        return lst;
    }

    public void getUserInfo_update(UserLookupDTO userInfo, String prop){}

    public void securityCheck_update(UserInfoDTO userInfo, String s){

    }

    public List<UserInfoDTO> securityCheck(String user, String password) {
        ArrayList<UserInfoDTO> lst = new ArrayList<>();
        lst.add(new UserInfoDTO(1L));
        return lst;
    }

}
