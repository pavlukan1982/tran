package com.surancebay.servlet.datamodel;

import java.util.List;

/**
 * Created by Viachaslau Parfenchyk on 24.09.2015.
 */
public class PanelElement {

    private String id;

    private String header;

    private List<String> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "PanelElement{" +
                "header='" + header + '\'' +
                ", id='" + id + '\'' +
                ", roles=" + roles +
                '}';
    }
}
