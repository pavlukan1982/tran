package com.surancebay.testrest;

import org.apache.cxf.jaxrs.ext.MessageContext;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;


/**
 * Created by pavlyukevich on 05.11.2015.
 */

@Path("/test")
public class RestResource {

    @Context
    private MessageContext mc;

    @GET
    @Path("{name}")
    public String getTest(@PathParam("name") String name) {
        return "Hello " + name + "!";
    }

    @GET
    public String getTestParam(@QueryParam("name") String name) {
        return "Hello " + name + "!!!";
    }
}
