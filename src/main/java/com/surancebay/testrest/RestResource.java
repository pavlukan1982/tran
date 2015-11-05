package com.surancebay.testrest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by pavlyukevich on 05.11.2015.
 */

@Path("/test")
public class RestResource {

    @GET
    @Produces("text/plain")
    public String getTest() {
        return "Hello!";
    }
}
