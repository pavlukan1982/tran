package com.surancebay.servlet.datamodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viachaslau Parfenchyk on 28.09.2015.
 */
public class PanelsData {
    private List<PanelBoxData> panelBoxDataList;

    public PanelsData() {
        this.panelBoxDataList = new ArrayList<PanelBoxData>();
    }

    public List<PanelBoxData> getPanelBoxDataList() {
        return panelBoxDataList;
    }

    public void setPanelBoxDataList(List<PanelBoxData> panelBoxDataList) {
        this.panelBoxDataList = panelBoxDataList;
    }

}
