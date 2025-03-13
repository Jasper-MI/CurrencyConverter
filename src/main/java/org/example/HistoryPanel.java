package org.example;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

//HistoryPanel displays the last conversions
public class HistoryPanel extends JPanel{

    HistoryPanel(PanelReloadListener panelReloadListenerMyFrame){
        //needed to update MyFrame when clearing history
        initializeComponents(panelReloadListenerMyFrame);

    }

    //builds the UI
    private void initializeComponents(PanelReloadListener panelReloadListenerMyFrame){


        //Header elements
        JLabel title = new JLabel("History");
        JButton deleteHistoryButton = new JButton("Clear History");
        deleteHistoryButton.setBackground(new Color(239, 116, 116));

        //Header
        JPanel header = new JPanel();
        title.setFont(new Font("Calibri", Font.BOLD, 22));

        //define what happens when the "Clear History" button is pressed
        deleteHistoryButton.addActionListener(e -> {
            if (e.getSource()==deleteHistoryButton) {
                File dir = new File("history"); //files in the "history" directory should be deleted

                try {
                    for(File file : Objects.requireNonNull(dir.listFiles())){
                        if(!file.isDirectory()) { //only delete if the file is not a directory
                            file.delete();
                            System.out.println(file); //for debugging
                        }
                    }
                    System.out.println(Arrays.toString(dir.listFiles()));
                    //Refresh the whole UI (also deletes the current conversion)
                    panelReloadListenerMyFrame.reloadPanel();

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //adding title and button to the header
        header.add(title);
        header.add(deleteHistoryButton);


        //Body
        JPanel body = new JPanel();

//        long fileCount = 0;
//        try (Stream<Path> files = Files.list(Path.of("history"))){
//            fileCount = files.count();
//        } catch (IOException e) {
//            throw new IOException();
//        }


        //display the last conversions via deserialization
        try{
            //set up the Format in which the numbers are to be displayed
            final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            symbols.setGroupingSeparator('\0');
            final DecimalFormat df = new DecimalFormat("0.00", symbols);
            df.setGroupingUsed(false);

            //create a File-list with alle files inside the "history" directory
            File[] files = new File("history").listFiles();

            //deserialize every file in the directory
            assert files != null;
            for (File filename : files) {
                String path = filename.getPath();
                String deserializeString = Serialize.deserializeSingle(path); //call the deserialize Methode
                String[] deserializeStringArray = deserializeString.split(";"); //split the return


                JLabel elementLabel = new JLabel(); //creating Label which displays the conversion
                double tempResult;
                double temResultRaw = Double.parseDouble(deserializeStringArray[3]);
                tempResult = Double.parseDouble(df.format(temResultRaw));
                //System.out.println(tempResult);
                elementLabel.setText(deserializeStringArray[2] + " " + deserializeStringArray[0].substring(0,3) + " to " + deserializeStringArray[1].substring(0,3) + " = " + tempResult);
                elementLabel.setBorder(new EmptyBorder(5,0,0,0));
                body.add(elementLabel); //adding the labels to the body

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));

        //set Layout
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbl.setConstraints(this, gbc);
        this.setLayout(gbl);

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(header, gbc);
        gbc.gridy = 1;
        this.add(body, gbc);
        this.setVisible(true);
    }


}
