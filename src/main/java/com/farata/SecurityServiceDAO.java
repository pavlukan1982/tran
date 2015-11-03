package com.farata;

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

    public List<UserLookupDTO> getUserInfo(long userId){

        List<UserLookupDTO> lst = new ArrayList<UserLookupDTO>();
        lst.add(new UserLookupDTO(10));
        return lst;
    }

    public void getUserInfo_update(UserLookupDTO userInfo, String prop){}
}
