package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MyFrame extends JFrame implements PanelReloadListener{




    public MyFrame(){
        initializeComponents(0,"", "");
    }

    public void initializeComponents(double lastCalcResult, String lastSecondCurrency, String placeHolderFirstNumber){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //History Panel
        HistoryPanel historyPanel = new HistoryPanel(this);
        historyPanel.setBorder(new EmptyBorder(10,10,10,10));

        JPanel historyWrapperPanel = new JPanel(); //Outer
        historyWrapperPanel.setBorder(BorderFactory.createMatteBorder(0,0,0, 2,Color.DARK_GRAY));
        historyWrapperPanel.add(historyPanel);


        //Main Panel
        MainPanel mainPanel = new MainPanel(this, lastCalcResult, lastSecondCurrency, placeHolderFirstNumber);

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

    @Override
    public void reloadPanel(double lastCalcResult, String lastSecondCurrency, String placeHolderFirstNumber){

        this.getContentPane().removeAll();
        initializeComponents(lastCalcResult, lastSecondCurrency, placeHolderFirstNumber);
        this.revalidate();
        this.repaint();

        System.out.println("MyFrame reloaded");
    }

    @Override
    public void reloadPanel(){
        this.getContentPane().removeAll();
        initializeComponents(0, "", "");
        this.revalidate();
        this.repaint();
        System.out.println("MyFrame reloaded");
    }


}
