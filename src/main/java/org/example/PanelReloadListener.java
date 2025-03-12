package org.example;

import java.io.IOException;

public interface PanelReloadListener {
    void reloadPanel(double lastCalcResult, String secondCurrency, String placeHolderFirstNumber) throws IOException;
    void reloadPanel() throws IOException;
}
