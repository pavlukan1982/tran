package com.farata;

import com.farata.dto.UserInfoDTO;
import com.farata.dto.UserInfoFirstLastNameDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrei on 16.10.2015.
 */
public class SecurityServiceDAO {
    public List<UserInfoFirstLastNameDTO> getUserInfoByProducerBgaId(long producerId, long bgaId, String bgaName){


        List<UserInfoFirstLastNameDTO> lst =  new ArrayList<>();
        if (producerId >= 1 ){
            lst.add(new UserInfoFirstLastNameDTO(10, "Andrew", "Baltimor"));
        }

        if (producerId >= 2) {
            lst.add(new UserInfoFirstLastNameDTO(10, "Eva", "Polna"));
        }

        return lst;
    }

    public List<UserInfoFirstLastNameDTO> getUserInfoByUserBgaId(String userEmail, long bgaId){
        return new ArrayList<>();
    }

    public List<UserInfoDTO> getUserInfo(long userId){

        List<UserInfoDTO> lst = new ArrayList<UserInfoDTO>();
        lst.add(new UserInfoDTO(10));
        return lst;
    }

    public void getUserInfo_update(UserInfoDTO userInfo, String prop){}
}
