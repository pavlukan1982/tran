package com.surancebay.backofficeadmin;

import com.surancebay.backofficeadmin.rest.service.impl.BackOfficeAdminResourceImpl;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Viachaslau Parfenchyk on 02.11.2015.
 */
public class BackOfficeAdminApp extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(BackOfficeAdminApp.class);

    @Override
    public Set<Object> getSingletons() {
        LOGGER.debug("getSingletons()");
        return new HashSet<Object>(Arrays.asList());
    }

    @Override
    public Set<Class<?>> getClasses() {
        LOGGER.debug("getClasses()");
        return new HashSet<Class<?>>(Arrays.asList(BackOfficeAdminResourceImpl.class, JacksonJsonProvider.class));
    }
}
