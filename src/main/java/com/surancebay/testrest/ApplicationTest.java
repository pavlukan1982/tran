package com.surancebay.testrest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pavlyukevich on 11.11.2015.
 */
@ApplicationPath("rest")
public class ApplicationTest extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<>(Arrays.asList(RestResource.class));
    }
}
