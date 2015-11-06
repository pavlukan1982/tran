package com.surancebay.testrest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by andrei on 07.11.2015.
 */

@Path("/")
public class RestResourseAdd {

    @GET
    public String get() {
        return "Additional";
    }
}
