package com.surancebay.backofficeadmin.rest.service.impl;

import com.farata.dto.UserLookupDTO;
import com.surancebay.backofficeadmin.rest.model.*;
import com.surancebay.backofficeadmin.rest.service.BackOfficeAdminResource;
import com.surancebay.backofficeadmin.service.BackOfficeAdminService;
import com.surancebay.backofficeadmin.service.impl.BackOfficeAdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by Viachaslau Parfenchyk on 03.11.2015.
 */
public class BackOfficeAdminResourceImpl implements BackOfficeAdminResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(BackOfficeAdminResourceImpl.class);

    private BackOfficeAdminService backOfficeAdminService = BackOfficeAdminServiceImpl.getInstance();

    private List<UserLookupJson> getUserLookupListByForm(UserLookupForm userLookupForm) {
        List<UserLookupJson> userLookupJsons = new ArrayList<UserLookupJson>();
        List<UserLookupDTO> userLookupDTOs = null;

        Long gaId = null;
        Long producerId = null;
        try {
            String gaIdString = userLookupForm.getGaId();
            if (gaIdString != null && !gaIdString.isEmpty()) {
                gaId = Long.valueOf(gaIdString);
            }
            String producerIdString = userLookupForm.getProducerId();
            producerId = producerIdString == null ? null : Long.valueOf(producerIdString);
        } catch (NumberFormatException e) {

        }

        if (userLookupForm.getLookupType().equalsIgnoreCase(UserLookupType.PRODUCER.toString())) {
            userLookupDTOs = backOfficeAdminService.getUserLookupListByProducer(producerId, gaId, userLookupForm.getGaName());
        } else if (userLookupForm.getLookupType().equalsIgnoreCase(UserLookupType.ADMIN.toString())) {
            userLookupDTOs = backOfficeAdminService.getUserLookupListByAdmin(userLookupForm.getGaEmail(), gaId);
        }
        if (userLookupDTOs != null && userLookupDTOs.size() > 0) {
            for (UserLookupDTO dto : userLookupDTOs) {
                UserLookupJson json = new UserLookupJson().copyFromDTO(dto);
                userLookupJsons.add(json);
            }
        }
        return userLookupJsons;
    }

    @Override
    public UserLookupResultJson getUserLookupList(UserLookupForm userLookupForm) {
        LOGGER.debug(String.format("userLookupByForm(%s)", userLookupForm.toString()));
        UserLookupResultJson resultJson = new UserLookupResultJson();
        List<UserLookupJson> userLookupJsons = getUserLookupListByForm(userLookupForm);
        if (userLookupJsons.size() > 0){
            resultJson.setStatus(ResultStatus.OK);
            resultJson.setInfoMessage(String.format("Founded %s record(s)",userLookupJsons.size()));
        } else {
            resultJson.setStatus(ResultStatus.ERROR);
            resultJson.setAlertMessage("Can't find any user by given criteria!");
        }
        resultJson.setUserLookupJsons(userLookupJsons);
        return resultJson;
    }

    @Override
    public BaseResultJson unlockUserAccount(Long userId) {
        LOGGER.debug("Try to unlock user account by id #{}", userId);
        BaseResultJson baseResultJson = new BaseResultJson();
        if (backOfficeAdminService.unlockUserAccount(userId)) {
            baseResultJson.setStatus(ResultStatus.OK);
            baseResultJson.setInfoMessage(String.format("A user account #%s successfully unlocked.", userId));
        } else {
            baseResultJson.setStatus(ResultStatus.ERROR);
            baseResultJson.setAlertMessage(String.format("Can't unlock user account by id = %s", userId));
        }
        return baseResultJson;
    }

}
