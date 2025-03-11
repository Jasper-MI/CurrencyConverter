package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class MyFrame extends JFrame{

    public MyFrame() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLayout(new BorderLayout());


        //History Panel
        HistoryPanel historyPanel = new HistoryPanel();
        historyPanel.setBorder(new EmptyBorder(10,10,10,10));

        JPanel historyWrapperPanel = new JPanel(); //Outer
        historyWrapperPanel.setBorder(BorderFactory.createMatteBorder(0,0,0, 2,Color.DARK_GRAY));
        historyWrapperPanel.add(historyPanel);


        //Main Panel
        MainPanel mainPanel = new MainPanel();

        //set Layout
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbl.setConstraints(this, gbc);

        this.setLayout(gbl);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(historyWrapperPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        this.add(mainPanel, gbc);
        this.pack();
        this.setVisible(true);
    }



}
