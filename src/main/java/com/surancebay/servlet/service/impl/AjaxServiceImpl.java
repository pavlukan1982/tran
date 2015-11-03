package com.surancebay.servlet.service.impl;

import com.google.gson.GsonBuilder;
import com.surancebay.servlet.datamodel.AjaxServiceResult;
import com.surancebay.servlet.service.AjaxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Viachaslau Parfenchyk on 27.10.2015.
 * AjaxService implementation.
 */
public class AjaxServiceImpl implements AjaxService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AjaxServiceImpl.class);

    private final static AjaxService INSTANCE = new AjaxServiceImpl();

    public static AjaxService getInstance() {
        return INSTANCE;
    }

    @Override
    public String buildJsonString(AjaxServiceResult ajaxServiceResult) {
        String result = null;
        if (ajaxServiceResult != null) {
            result = new GsonBuilder().create().toJson(ajaxServiceResult).toString();
            LOGGER.debug("Builded Json string");
        }
        return result;
    }

}
