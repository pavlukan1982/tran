package com.surancebay.servlet.service;

import com.surancebay.servlet.datamodel.AjaxServiceResult;

/**
 * Created by Viachaslau Parfenchyk on 27.10.2015.
 * Build Json String for AjaxServiceResult object.
 */
public interface AjaxService {

    String buildJsonString(AjaxServiceResult ajaxServiceResult);

}
