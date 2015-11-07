package com.surancebay.testrest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by andrei on 07.11.2015.
 */

@Path("/")
public class RestResourceAdd {

    @GET
    public String get(@QueryParam("name") String name) {
        return "Additional rest service" + "\n" +
                "name : " + name ;
    }

    @GET
    public String get(@QueryParam("name") String name, @QueryParam("family") String family) {
        return "Additional rest service" + "\n" +
                "name : " + name + "\n" +
                "family : " + family;
    }

}
