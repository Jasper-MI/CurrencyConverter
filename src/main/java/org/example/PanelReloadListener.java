package org.example;

import java.io.IOException;

//interface, which is used for communication between different Panels to refresh
public interface PanelReloadListener {
    void reloadPanel(double lastCalcResult, String secondCurrency, String placeHolderFirstNumber) throws IOException;
    void reloadPanel() throws IOException;
}
