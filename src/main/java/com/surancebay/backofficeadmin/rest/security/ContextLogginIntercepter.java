package com.surancebay.backofficeadmin.rest.security;

import org.apache.cxf.common.security.SimpleGroup;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.security.DefaultSecurityContext;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import javax.security.auth.Subject;

/**
 * Created by pavlyukevich on 17.11.2015.
 */
public class ContextLogginIntercepter extends AbstractPhaseInterceptor {

    public ContextLogginIntercepter() {
        super(Phase.PRE_INVOKE);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        Subject subject = new Subject();
        subject.getPrincipals().add(new SimpleGroup("Admin"));
        subject.getPrincipals().add(new SimpleGroup("Manager"));
        message.put("org.apache.cxf.security.SecurityContext", new DefaultSecurityContext(subject));
    }
}
