package com.surancebay.backofficeadmin.rest.service;

import com.surancebay.backofficeadmin.rest.model.BaseResultJson;
import com.surancebay.backofficeadmin.rest.model.UserLookupForm;
import com.surancebay.backofficeadmin.rest.model.UserLookupResultJson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Viachaslau Parfenchyk on 02.11.2015.
 */
public interface BackOfficeAdminResource {

    /**
     * Lookup user by the search criteria defined in UserLookupUser object.
     * @param userLookupForm
     * @return List of UserLookupDTO
     */
    @POST
    @Path("lookup")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    UserLookupResultJson getUserLookupList(@FormParam("") UserLookupForm userLookupForm);

    /**
     * Unlock user account using BackOfficeAdminService method.
     * @param userId
     * @return
     */
    @GET
    @Path("unlockaccount/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    BaseResultJson unlockUserAccount(@PathParam("userId") Long userId);

}
