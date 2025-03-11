package org.example;

import javax.swing.*;
import java.io.IOException;

public interface PanelReloadListener {
    void reloadPanel(double lastCalcResult, String secondCurrency) throws IOException;
    void reloadPanel() throws IOException;
}
