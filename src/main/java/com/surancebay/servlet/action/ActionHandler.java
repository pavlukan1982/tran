package com.surancebay.servlet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Viachaslau Parfenchyk on 22.09.2015.
 */
public interface ActionHandler {
    void handle(HttpServletRequest req, HttpServletResponse resp);
}
