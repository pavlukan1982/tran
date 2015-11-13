package com.surancebay.testrest;

import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet;

import javax.servlet.ServletConfig;

/**
 * Created by andrei on 07.11.2015.
 */
public class ExtendedJaxrsServlet extends CXFNonSpringJaxrsServlet{

    private static final String SOURCE_COMPARATOR = "jaxrs.comparator";

    protected void setExtensions(JAXRSServerFactoryBean bean, ServletConfig servletConfig) {
        super.setExtensions(bean, servletConfig);

        bean.setResourceComparator(new QueryMatcher());
    }


}
