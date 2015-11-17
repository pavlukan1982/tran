package com.surancebay.backofficeadmin.service.impl;

import com.farata.SecurityServiceDAO;
import com.farata.custom.SecurityExtender;
import com.farata.dao.DaoServices;
import com.farata.dto.UserInfoDTO;
import com.farata.dto.UserLookupDTO;
import com.surancebay.backofficeadmin.service.BackOfficeAdminService;
import com.surancebay.security.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Viachaslau Parfenchyk on 03.11.2015.
 */
public class BackOfficeAdminServiceImpl implements BackOfficeAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BackOfficeAdminServiceImpl.class);

    private static final BackOfficeAdminService INSTANCE = new BackOfficeAdminServiceImpl();

    private static final SecurityServiceDAO securityService = new SecurityServiceDAO();

    private BackOfficeAdminServiceImpl() {

    }

    public static BackOfficeAdminService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<UserLookupDTO> getUserLookupListByProducer(Long producerId, Long gaId, String gaName) {
        List<UserLookupDTO> userLookupList = new ArrayList<UserLookupDTO>();
        if (userHasAnyRole(new String[]{"ADMIN", "MANAGER"})) {
            gaId = gaId == null ? 0L : gaId;
            if (producerId > 0) {
                userLookupList = securityService.getUserByProducerIdAndBga(producerId, gaId, gaName);
            }
        }
        return userLookupList;
    }

    @Override
    public List<UserLookupDTO> getUserLookupListByAdmin(String gaEmail, Long gaId) {
        List<UserLookupDTO> userLookupList = new ArrayList<UserLookupDTO>();
        if (userHasRole("ADMIN")) {
            gaId = gaId == null ? 0L : gaId;
            if (gaEmail != null || gaId > 0) {
                userLookupList = securityService.getUserByBgaAdminEmailOrId(gaEmail, gaId);
            }
        }
        return userLookupList;
    }

    @Override
    public boolean unlockUserAccount(Long userId) {
        boolean result = false;
        //TODO not good idea using acces here. Move it to the filter.
        if (userHasRole("ADMIN")) {
            UserInfoDTO userInfo = null;
            if (userId > 0) {
                SecurityExtender securityExtender = new SecurityExtender();
                userInfo = DaoServices.firstOrNull(securityExtender.getUserInfo(userId));
                if (userInfo != null) {
                    userInfo.setfailedLoginCount(0L);
                    // rolling back passwordChangedOn if necessary
                    if (userInfo.passwordChangedOn != null) {
                        ZonedDateTime passwordDate = ZonedDateTime.ofInstant(userInfo.passwordChangedOn.toInstant(),
                                ZoneId.systemDefault());

                        ZonedDateTime now = ZonedDateTime.now();
                        Duration duration = Duration.between(passwordDate, now);

                        // rolling back only if it is less than 1 day
                        if (1 > duration.toDays()) {
                            Instant instant = (LocalDate.now().minusDays(3L)).atTime(11, 0).atZone(ZoneId.systemDefault()).toInstant();
                            userInfo.passwordChangedOn = Date.from(instant);

                            securityExtender.getUserInfo_update(userInfo, "failedLoginCount", "passwordChangedOn");
                        } else {
                            securityExtender.getUserInfo_update(userInfo, "failedLoginCount");
                        }
                    } else {
                        securityExtender.getUserInfo_update(userInfo, "failedLoginCount");
                    }
                    userInfo = DaoServices.firstOrNull(securityExtender.getUserInfo(userId));
                    result = true;
                }
            }
        }
        return result;
    }

    private boolean isAuthenticatedUser() {
        Long userId = SecurityContext.getUserId();
        boolean result = (userId != null && userId > 0);
        return result;
    }

    private boolean userHasRole(String role) {
        boolean hasRole = false;
        if (role != null && isAuthenticatedUser()) {
            List<String> userRoles = SecurityContext.getUserRoles();
            for (String userRole : userRoles) {
                hasRole = userRole.equalsIgnoreCase(role);
                if (hasRole) {
                    break;
                }
            }
        }
        return hasRole;
    }

    private boolean userHasAnyRole(String[] roles) {
        boolean hasAnyRole = false;
        if (roles.length > 0) {
            for (String role : roles) {
                if (userHasRole(role)) {
                    hasAnyRole = true;
                    break;
                }
            }
        }
        return hasAnyRole;
    }
}
