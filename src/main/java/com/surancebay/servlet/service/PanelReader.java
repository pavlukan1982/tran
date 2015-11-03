package com.surancebay.servlet.service;

import com.surancebay.servlet.datamodel.PanelsData;

import java.io.InputStream;

/**
 * Created by Viachaslau Parfenchyk on 28.09.2015.
 */
public interface PanelReader {

    PanelsData read(InputStream inputStream);

}
