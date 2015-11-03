package com.surancebay.servlet.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.surancebay.servlet.datamodel.PanelBoxData;
import com.surancebay.servlet.datamodel.PanelsData;
import com.surancebay.servlet.service.PanelReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Viachaslau Parfenchyk on 28.09.2015.
 */
public class PanelReaderImpl implements PanelReader {

    private final static Logger LOGGER = LoggerFactory.getLogger(PanelServiceImpl.class);
    private final static PanelReader INSTANCE = new PanelReaderImpl();

    private PanelsData panelsData;

    private PanelReaderImpl() {
    }

    public static PanelReader getInstance() {
        return INSTANCE;
    }

    public PanelsData readJson(Reader reader) {
        PanelsData result = new PanelsData();
        if (reader != null) {
            Gson gson = new Gson();

            Type listType = new TypeToken<List<PanelBoxData>>() {
            }.getType();
            List<PanelBoxData> panelBoxDataList = gson.fromJson(reader, listType);
            if (panelBoxDataList != null) {
                result.setPanelBoxDataList(panelBoxDataList);
            } else {
                LOGGER.debug("panelBoxDataList is NULL!");
            }
        } else {
            LOGGER.error("Try to read with NULL reader!");
        }
        return result;
    }

    @Override
    public PanelsData read(InputStream inputStream) {
        LOGGER.debug("read PanelsData from the inputStream");
        BufferedReader br = null;
        InputStreamReader in = new InputStreamReader(inputStream);
        LOGGER.debug("in: " + in);
        br = new BufferedReader(in);
        LOGGER.debug("br: " + br);
        panelsData = readJson(br);
        return panelsData;
    }
}
