package com.surancebay.backofficeadmin.rest.model;

import com.farata.dto.UserLookupDTO;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Viachaslau Parfenchyk on 03.11.2015.
 */
@XmlRootElement
public class UserLookupJson extends UserLookupDTO {

    @JsonIgnore
    public UserLookupJson copyFromDTO(UserLookupDTO dto){
        this.setuserId(dto.getuserId());
        this.setfailedLoginCount(dto.getfailedLoginCount());
        this.setpasswordChangedOn(dto.getpasswordChangedOn());
        this.setlastLoginAttempt(dto.getlastLoginAttempt());
        this.setfirstName(dto.getfirstName());
        this.setgaId(dto.getgaId());
        this.setgaName(dto.getgaName());
        return this;
    }
}
