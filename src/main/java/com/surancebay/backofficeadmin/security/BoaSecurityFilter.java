package com.surancebay.backofficeadmin.security;

import com.surancebay.security.SecurityContext;
import com.surancebay.servlet.action.enums.Action;
import com.surancebay.servlet.action.impl.AuthenticateActionHandler;
import com.surancebay.servlet.action.impl.LoginActionHandler;
import com.surancebay.servlet.service.settings.ServletSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Viachaslau Parfenchyk on 13.11.2015.
 */
public class BoaSecurityFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(BoaSecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        SecurityContext context = SecurityContext.getInstance();
        if (!context.isLoggedIn()) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String loginJsp = ServletSettings.getTemplateName(ServletSettings.LOGIN_PAGE);
            String actionString = request.getParameter(ServletSettings.ACTION_PARAM_NAME);
            Action action = actionString==null?Action.LOGIN:Action.valueOf(actionString.toUpperCase());
            if (action!=Action.LOGIN) {
                RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(loginJsp);
                dispatcher.forward(servletRequest, servletResponse);
                return;
            } else {
                new LoginActionHandler().handle(request,response);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {
    }
}
