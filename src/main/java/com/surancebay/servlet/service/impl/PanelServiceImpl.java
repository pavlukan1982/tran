package com.surancebay.servlet.service.impl;

import com.surancebay.security.SecurityContext;
import com.surancebay.servlet.action.enums.Action;
import com.surancebay.servlet.datamodel.*;
import com.surancebay.servlet.service.PanelReader;
import com.surancebay.servlet.service.PanelService;
import com.surancebay.servlet.service.settings.ServletSettings;
import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.*;

/**
 * Created by Viachaslau Parfenchyk on 24.09.2015.
 */
public class PanelServiceImpl implements PanelService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PanelServiceImpl.class);
    private final static PanelService INSTANCE = new PanelServiceImpl();

    private Map<String, PanelBox> panelBoxes = new HashMap<String, PanelBox>();

    private Map<String, PanelItem> panelItems = new HashMap<String, PanelItem>();

    private Map<String, String> jspMap = new HashMap<>();

    PanelReader panelReader = PanelReaderImpl.getInstance();

    private PanelServiceImpl() {
    }

    public static PanelService getInstance() {
        return INSTANCE;
    }

    @Override
    public PanelBox addPanelBox(String boxId) {
        PanelBox result = panelBoxes.get(boxId);
        if (result == null) {
            result = new PanelBox();
            result.setId(boxId);
            result.setHeader(boxId);
            panelBoxes.put(result.getId(), result);
        }
        return result;
    }

    @Override
    public void removePanelBox(String boxId) {
        panelBoxes.remove(boxId);
    }

    @Override
    public PanelBox getPanelBox(String boxId) {
        return panelBoxes.get(boxId);
    }

    @Override
    public List<PanelBox> getAllBoxes() {
        LOGGER.debug("getAllBoxes");
        return new ArrayList<PanelBox>(panelBoxes.values());
    }

    @Override
    public PanelItem addPanelItem(String itemId) {
        PanelItem result = panelItems.get(itemId);
        if (result == null) {
            result = new PanelItem();
            result.setId(itemId);
            result.setText(itemId);
            panelItems.put(result.getId(), result);
        }
        return result;
    }

    @Override
    public PanelItem getPanelItem(String itemId) {
        return panelItems.get(itemId);
    }

    @Override
    public void removePanelItem(String itemId) {
        panelItems.remove(itemId);
    }

    @Override
    public List<PanelItem> getBoxItems(String panelBoxId) {
        LOGGER.debug(String.format("getBoxItems(%s)", panelBoxId));
        List<PanelItem> result = new ArrayList<PanelItem>();
        Iterator<PanelItem> it = panelItems.values().iterator();
        while (it.hasNext()) {
            PanelItem item = it.next();
            LOGGER.debug(String.format("%s.panelBoxId = %s", item.getId(), item.getPanelBoxId()));
            if (item.getPanelBoxId().equals(panelBoxId)) {
                result.add(item);
            }
        }
        return result;
    }

    private boolean validPanelElementRole(PanelElement element, String role) {
        boolean result = element.getRoles() == null;
        if (!result && element.getRoles().size() > 0) {
            //LOGGER.debug(String.format("Is %s contains the role '%s'?", element.getRoles(), role));
            result = element.getRoles().contains(role);
        }
        return result;
    }

    private boolean validPanelElementRoles(PanelElement element, List<String> roles) {
        boolean result = false;
        for (String role : roles) {
            //LOGGER.debug(String.format("Check access to element #%s (%s) for roles: ", element.getId(), element.getClass().getSimpleName(), roles));
            result = validPanelElementRole(element, role);
            if (result) {
                break;
            }
        }
        return result;
    }

    @Override
    public List<PanelItem> getAllItems(List<PanelBoxData> listBoxData) {
        List<PanelItem> result = new ArrayList<PanelItem>();
        for (PanelBoxData box : listBoxData) {
            result.addAll(box.getPanelItemList());
        }
        return result;
    }

    @Override
    public List<PanelItem> getAllItemsByRoles(List<PanelBoxData> listBoxData) {
        List<PanelBoxData> panelBoxDataList = PanelServiceImpl.getInstance().filterPanelBoxDataByRoles(listBoxData);
        return getAllItems(panelBoxDataList);
    }

    @Override
    public List<PanelBoxData> filterPanelBoxDataByRoles(List<PanelBoxData> listBoxData) {
        List<PanelBoxData> result = listBoxData;
        // read roles from the context
        List<String> roles = SecurityContext.getUserRoles();
        Iterator<PanelBoxData> itBoxData = result.iterator();
        while (itBoxData.hasNext()) {
            PanelBoxData boxData = itBoxData.next();
            //LOGGER.debug(String.format("Check access to box #%s for roles: %s", boxData.getId(), roles));
            boolean validBoxRole = validPanelElementRoles(boxData, roles);
            if (validBoxRole) {
                Iterator<PanelItem> itItem = boxData.getPanelItemList().iterator();
                while (itItem.hasNext()) {
                    PanelItem item = itItem.next();
                    //LOGGER.debug(String.format("Check access to #%s.#%s", boxData.getId(), item.getId()));
                    boolean validItemRole = validPanelElementRoles(item, roles);
                    if (!validItemRole) {
                        itItem.remove();
                    }
                }
            } else {
                LOGGER.debug(String.format("Havn't access to #%s!", boxData.getId()));
            }
            // havn't any child elements
            if (!validBoxRole || boxData.getPanelItemList().size() == 0) {
                itBoxData.remove();
                LOGGER.debug("Removed hole box with id = " + boxData.getId());
            }
        }
        return result;
    }

    @Override
    public PanelsData getPanelsData(InputStream inputStream) {
        PanelsData panelsData = new PanelsData();
        panelsData = panelReader.read(inputStream);
        if (panelsData != null) {
            treatPanelsDataUrl(panelsData);
        } else {
            LOGGER.error("Can't read panels data from {}", inputStream);
        }
        return panelsData;
    }

    private void treatPanelsDataUrl(PanelsData panelsData) {
        if (panelsData != null) {
            List<PanelBoxData> panelBoxDataList = panelsData.getPanelBoxDataList();
            if (panelBoxDataList != null) {
                for (PanelBoxData panelBoxData : panelBoxDataList) {
                    List<PanelItem> panelItemList = panelBoxData.getPanelItemList();
                    if (panelItemList != null) {
                        for (PanelItem panelItem : panelItemList) {
                            LOGGER.debug("panelItem: " + panelItem);
                            if (panelItem.getUrl() == null) {
                                String url = null;
                                if (panelItem.getActionString() != null) {
                                    boolean validAction = EnumUtils.isValidEnum(Action.class, panelItem.getActionString().toUpperCase());
                                    if (validAction) {
                                        Action action = Action.valueOf(panelItem.getActionString().toUpperCase());
                                        if (action != Action.JSP) {
                                            url = ServletSettings.getActionUrl(action);
                                        } else {
                                            url = ServletSettings.getJspSourceUrl(panelItem.getId());
                                        }
                                    } else {
                                        LOGGER.error("Invalid action ({}) in panelsData object!", panelItem.getActionString());
                                    }
                                } else {
                                    url = ServletSettings.getJspSourceUrl(panelItem.getId());
                                }
                                LOGGER.debug("url = " + url);
                                panelItem.setUrl(url);
                            }
                        }
                    }
                }
            }
        }
    }

    public String getJspSource(String jspName) {
        return ServletSettings.getJspFilename(jspMap.get(jspName));
    }
}
