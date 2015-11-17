package com.surancebay.backofficeadmin.service;

import com.farata.dto.UserLookupDTO;

import java.util.List;

/**
 * Created by Viachaslau Parfenchyk on 03.11.2015.
 */
public interface BackOfficeAdminService {

    /**
     * Lookup user by the search criteria: producerId, gaId, gaName.
     * @param producerId
     * @param gaId
     * @param gaName
     * @return List of UserLookupDTO
     */
    List<UserLookupDTO> getUserLookupListByProducer(Long producerId, Long gaId, String gaName);

    /**
     * Lookup user by the search criteria: producerId, gaId, gaName.
     * @param userEmail
     * @param bgaId
     * @return List of UserLookupDTO
     */
    List<UserLookupDTO> getUserLookupListByAdmin(String userEmail, Long bgaId);

    /**
     * Unlock user account
     * @param userId
     * @return
     */
    boolean unlockUserAccount(Long userId);

}
