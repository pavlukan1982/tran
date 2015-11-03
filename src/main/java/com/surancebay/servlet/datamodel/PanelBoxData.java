package com.surancebay.servlet.datamodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viachaslau Parfenchyk on 28.09.2015.
 */
public class PanelBoxData extends PanelBox {

    private List<PanelItem> panelItemList;

    public PanelBoxData() {
        this.panelItemList = new ArrayList<PanelItem>();
    }

    public List<PanelItem> getPanelItemList() {
        return panelItemList;
    }

    public void setPanelItemList(List<PanelItem> panelItemList) {
        this.panelItemList = panelItemList;
    }

}
