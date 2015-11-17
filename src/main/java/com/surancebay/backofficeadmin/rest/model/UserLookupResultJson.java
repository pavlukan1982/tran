package com.surancebay.backofficeadmin.rest.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viachaslau Parfenchyk on 11.11.2015.
 */
@XmlRootElement
public class UserLookupResultJson extends BaseResultJson{

    private List<UserLookupJson> userLookupJsons = new ArrayList<UserLookupJson>();

    public List<UserLookupJson> getUserLookupJsons() {
        return userLookupJsons;
    }

    public void setUserLookupJsons(List<UserLookupJson> userLookupJsons) {
        this.userLookupJsons = userLookupJsons;
    }
}
