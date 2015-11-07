package com.surancebay.testrest;

import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet;

import javax.servlet.ServletConfig;

/**
 * Created by andrei on 07.11.2015.
 */
public class ExtendedJaxrsServlet extends CXFNonSpringJaxrsServlet{

    private static final String EXTENSIONS_PARAM = "jaxrs.extensions";
    private static final String LANGUAGES_PARAM = "jaxrs.languages";
    private static final String PROPERTIES_PARAM = "jaxrs.languages";
    private static final String SOURCE_COMPARATOR = "jaxrs.comparator";

    protected void setExtensions(JAXRSServerFactoryBean bean, ServletConfig servletConfig) {
        bean.setExtensionMappings(handleMapSequence(servletConfig.getInitParameter(EXTENSIONS_PARAM)));
        bean.setLanguageMappings(handleMapSequence(servletConfig.getInitParameter(LANGUAGES_PARAM)));
        bean.setProperties(CastUtils.cast(
                handleMapSequence(servletConfig.getInitParameter(PROPERTIES_PARAM)),
                String.class, Object.class));

        bean.setResourceComparator(new QueryMatcher());
    }


}
