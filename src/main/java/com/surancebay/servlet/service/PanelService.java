package com.surancebay.servlet.service;

import com.surancebay.servlet.datamodel.*;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Viachaslau Parfenchyk on 24.09.2015.
 */
public interface PanelService {

    PanelBox addPanelBox(String boxId);
    PanelBox getPanelBox(String boxId);
    void removePanelBox(String boxId);

    PanelItem addPanelItem(String itemId);
    PanelItem getPanelItem(String itemId);
    void removePanelItem(String itemId);

    List<PanelBox> getAllBoxes();

    List<PanelItem> getBoxItems(String panelBoxId);

    List<PanelBoxData> filterPanelBoxDataByRoles(List<PanelBoxData> listBoxData);

    List<PanelItem> getAllItems(List<PanelBoxData> listBoxData);

    List<PanelItem> getAllItemsByRoles(List<PanelBoxData> listBoxData);

    PanelsData getPanelsData(InputStream inputStream);
}
