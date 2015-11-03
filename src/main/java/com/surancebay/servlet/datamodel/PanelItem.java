package com.surancebay.servlet.datamodel;

/**
 * Created by Viachaslau Parfenchyk on 24.09.2015.
 */
public class PanelItem extends  PanelElement {

    private String actionString;

    private String url;
    private String text;

    private String panelBoxId;

    public String getActionString() {
        return actionString;
    }

    public void setActionString(String actionString) {
        this.actionString = actionString;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPanelBoxId() {
        return panelBoxId;
    }

    public void setPanelBoxId(String panelBoxId) {
        this.panelBoxId = panelBoxId;
    }

    @Override
    public String toString() {
        return super.toString()+", PanelItem{" +
                "actionString='" + actionString + '\'' +
                ", panelBoxId='" + panelBoxId + '\'' +
                ", url='" + url + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
