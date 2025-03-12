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




public class HistoryPanel extends JPanel{

    HistoryPanel(PanelReloadListener panelReloadListenerMyFrame){
        //needed to update MyFrame when clearing history
        initializeComponents(panelReloadListenerMyFrame);

    }

    private void initializeComponents(PanelReloadListener panelReloadListenerMyFrame){


        //Header elements
        JLabel title = new JLabel("History");
        JButton deleteHistoryButton = new JButton("Clear History");

        //Header
        JPanel header = new JPanel();
        title.setFont(new Font("Calibri", Font.BOLD, 22));

        deleteHistoryButton.addActionListener(e -> {
            if (e.getSource()==deleteHistoryButton) {
                File dir = new File("history");

                try {
                    for(File file : Objects.requireNonNull(dir.listFiles())){
                        if(!file.isDirectory()) {
                            file.delete();
                            System.out.println(file);
                        }
                    }
                    System.out.println(Arrays.toString(dir.listFiles()));
                    //reloadPanel();
                    panelReloadListenerMyFrame.reloadPanel();

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

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


        try{
            final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            symbols.setGroupingSeparator('\0');
            final DecimalFormat df = new DecimalFormat("0.00", symbols);
            df.setGroupingUsed(false);

            File[] files = new File("history").listFiles();

            assert files != null;
            for (File filename : files) {
                String path = filename.getPath();
                String deserializeString = Serialize.deserializeSingle(path);
                String[] deserializeStringArray = deserializeString.split(";");


                JLabel elementLabel = new JLabel();
                double tempResult;
                double temResultRaw = Double.parseDouble(deserializeStringArray[3]);
                tempResult = Double.parseDouble(df.format(temResultRaw));
                System.out.println(tempResult);
                elementLabel.setText(deserializeStringArray[2] + " " + deserializeStringArray[0].substring(0,3) + " to " + deserializeStringArray[1].substring(0,3) + " = " + tempResult);
                elementLabel.setBorder(new EmptyBorder(5,0,0,0));
                body.add(elementLabel);

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

//    @Override
//    public void reloadPanel() throws IOException {
//        this.removeAll();
//        initializeComponents();
//        this.revalidate();
//        this.repaint();
//        System.out.println("HistoryPanel reloaded");
//    }
//
//    @Override
//    public void reloadPanel(double lastCalcResult, String secondCurrency) throws IOException {
//
//    }



}
