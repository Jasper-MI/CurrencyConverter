package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MainPanel extends JPanel implements ActionListener{


    //Jframe elements
    JLabel amountLabel;
    JLabel text2;
    JTextField firstNumber;
    JButton submitButton;
    JComboBox firstCurrency;
    double calcResult = 0;
    String resultLabelCurrency;

    JComboBox secondCurrency;
    JLabel resultLabel;

    //needed to update MyFrame when Result is added
    private PanelReloadListener panelReloadListenerMyFrame;


    public MainPanel(PanelReloadListener panelReloadListenerMyFrame, double lastCalcResult, String lastSecondCurrency){
        this.calcResult = lastCalcResult;
        this.resultLabelCurrency = lastSecondCurrency;
        initializeComponents(panelReloadListenerMyFrame);
    }

    private void initializeComponents(PanelReloadListener panelReloadListenerMyFrame) {
        this.panelReloadListenerMyFrame = panelReloadListenerMyFrame;



        amountLabel = new JLabel("Amount: ");

        firstNumber = new JTextField(5);

        String[] currency = {"AED (United Arab Emirates Dirham)", "AFN (Afghan Afghani)", "ALL (Albanian Lek)", "AMD (Armenian Dram)", "ANG (Netherlands Antillean Guilder)", "AOA (Angolan Kwanza)", "ARS (Argentine Peso)", "AUD (Australian Dollar)", "AWG (Aruban Florin)", "AZN (Azerbaijani Manat)", "BAM (Bosnia-Herzegovina Convertible Mark)", "BBD (Barbadian Dollar)", "BDT (Bangladeshi Taka)", "BGN (Bulgarian Lev)", "BHD (Bahraini Dinar)", "BIF (Burundian Franc)", "BMD (Bermudan Dollar)", "BND (Brunei Dollar)", "BOB (Bolivian Boliviano)", "BRL (Brazilian Real)", "BSD (Bahamian Dollar)", "BTC (Bitcoin)", "BTN (Bhutanese Ngultrum)", "BWP (Botswanan Pula)", "BYN (Belarusian Ruble)", "BYR (Belarusian Ruble, pre-2016)", "BZD (Belize Dollar)", "CAD (Canadian Dollar)", "CDF (Congolese Franc)", "CHF (Swiss Franc)", "CLF (Chilean Unit of Account (UF))", "CLP (Chilean Peso)", "CNH (Chinese Yuan (Offshore))", "CNY (Chinese Yuan)", "COP (Colombian Peso)", "CRC (Costa Rican Colón)", "CUC (Cuban Convertible Peso)", "CUP (Cuban Peso)", "CVE (Cape Verdean Escudo)", "CZK (Czech Republic Koruna)", "DJF (Djiboutian Franc)", "DKK (Danish Krone)", "DOP (Dominican Peso)", "DZD (Algerian Dinar)", "EEK (Estonian Kroon)", "EGP (Egyptian Pound)", "ERN (Eritrean Nakfa)", "ETB (Ethiopian Birr)", "EUR (Euro)", "FJD (Fijian Dollar)", "FKP (Falkland Islands Pound)", "GBP (British Pound Sterling)", "GEL (Georgian Lari)", "GGP (Guernsey Pound)", "GHS (Ghanaian Cedi)", "GIP (Gibraltar Pound)", "GMD (Gambian Dalasi)", "GNF (Guinean Franc)", "GTQ (Guatemalan Quetzal)", "GYD (Guyanaese Dollar)", "HKD (Hong Kong Dollar)", "HNL (Honduran Lempira)", "HRK (Croatian Kuna)", "HTG (Haitian Gourde)", "HUF (Hungarian Forint)", "IDR (Indonesian Rupiah)", "ILS (Israeli New Sheqel)", "IMP (Manx pound)", "INR (Indian Rupee)", "IQD (Iraqi Dinar)", "IRR (Iranian Rial)", "ISK (Icelandic Króna)", "JEP (Jersey Pound)", "JMD (Jamaican Dollar)", "JOD (Jordanian Dinar)", "JPY (Japanese Yen)", "KES (Kenyan Shilling)", "KGS (Kyrgystani Som)", "KHR (Cambodian Riel)", "KMF (Comorian Franc)", "KPW (North Korean Won)", "KRW (South Korean Won)", "KWD (Kuwaiti Dinar)", "KYD (Cayman Islands Dollar)", "KZT (Kazakhstani Tenge)", "LAK (Laotian Kip)", "LBP (Lebanese Pound)", "LKR (Sri Lankan Rupee)", "LRD (Liberian Dollar)", "LSL (Lesotho Loti)", "LYD (Libyan Dinar)", "MAD (Moroccan Dirham)", "MDL (Moldovan Leu)", "MGA (Malagasy Ariary)", "MKD (Macedonian Denar)", "MMK (Myanma Kyat)", "MNT (Mongolian Tugrik)", "MOP (Macanese Pataca)", "MRO (Mauritanian Ouguiya, pre-2018)", "MRU (Mauritanian Ouguiya)", "MTL (Maltese Lira)", "MUR (Mauritian Rupee)", "MVR (Maldivian Rufiyaa)", "MWK (Malawian Kwacha)", "MXN (Mexican Peso)", "MYR (Malaysian Ringgit)", "MZN (Mozambican Metical)", "NAD (Namibian Dollar)", "NGN (Nigerian Naira)", "NIO (Nicaraguan Córdoba)", "NOK (Norwegian Krone)", "NPR (Nepalese Rupee)", "NZD (New Zealand Dollar)", "OMR (Omani Rial)", "PAB (Panamanian Balboa)", "PEN (Peruvian Nuevo Sol)", "PGK (Papua New Guinean Kina)", "PHP (Philippine Peso)", "PKR (Pakistani Rupee)", "PLN (Polish Zloty)", "PYG (Paraguayan Guarani)", "QAR (Qatari Rial)", "RON (Romanian Leu)", "RSD (Serbian Dinar)", "RUB (Russian Ruble)", "RWF (Rwandan Franc)", "SAR (Saudi Riyal)", "SBD (Solomon Islands Dollar)", "SCR (Seychellois Rupee)", "SDG (Sudanese Pound)", "SEK (Swedish Krona)", "SGD (Singapore Dollar)", "SHP (Saint Helena Pound)", "SLL (Sierra Leonean Leone)", "SOS (Somali Shilling)", "SRD (Surinamese Dollar)", "SSP (South Sudanese Pound)", "STD (São Tomé and Príncipe Dobra, pre-2018)", "STN (São Tomé and Príncipe Dobra)", "SVC (Salvadoran Colón)", "SYP (Syrian Pound)", "SZL (Swazi Lilangeni)", "THB (Thai Baht)", "TJS (Tajikistani Somoni)", "TMT (Turkmenistani Manat)", "TND (Tunisian Dinar)", "TOP (Tongan Paʻanga)", "TRY (Turkish Lira)", "TTD (Trinidad and Tobago Dollar)", "TWD (New Taiwan Dollar)", "TZS (Tanzanian Shilling)", "UAH (Ukrainian Hryvnia)", "UGX (Ugandan Shilling)", "USD (United States Dollar)", "UYU (Uruguayan Peso)", "UZS (Uzbekistan Som)", "VES (Venezuelan Bolívar Soberano)", "VND (Vietnamese Dong)", "VUV (Vanuatu Vatu)", "WST (Samoan Tala)", "XAF (CFA Franc BEAC)", "XAG (Silver (troy ounce))", "XAU (Gold (troy ounce))", "XCD (East Caribbean Dollar)", "XDR (Special Drawing Rights)", "XOF (CFA Franc BCEAO)", "XPD (Palladium Ounce)", "XPF (CFP Franc)", "XPT (Platinum Ounce)", "YER (Yemeni Rial)", "ZAR (South African Rand)", "ZMW (Zambian Kwacha)"};
        firstCurrency = new JComboBox(currency);
        firstCurrency.addActionListener(this);

        text2 = new JLabel(" to ");

        secondCurrency = new JComboBox(currency);
        secondCurrency.addActionListener(this);

        ImageIcon resultIcon = new ImageIcon("media/pixel-art-game-currency-coin-vector.png");
        submitButton = new JButton(resultIcon);
        submitButton.addActionListener(this);
        //submitButton.setIcon(resultIcon);

        resultLabel = new JLabel("Result: " + calcResult + " " + resultLabelCurrency);


        //set Layout
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbl.setConstraints(this, gbc);
        this.setLayout(gbl);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,5,10,5);
        this.add(amountLabel, gbc);
        gbc.gridx = 1;
        this.add(firstNumber, gbc);
        gbc.gridx = 2;
        this.add(firstCurrency, gbc);
        gbc.gridx = 3;
        this.add(text2, gbc);
        gbc.gridx = 4;
        this.add(secondCurrency, gbc);
        gbc.gridx = 5;
        this.add(submitButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(resultLabel, gbc);
        //this.setBackground(new Color(255,255,0));
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource()==submitButton){

            submitButton.setIcon(new ImageIcon("media/coin_spin.gif"));

            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception{
                    try {
                        //creating Result object to calculate result
                        Result result = new Result(
                                (String) firstCurrency.getSelectedItem(),
                                (String) secondCurrency.getSelectedItem(),
                                Integer.parseInt(firstNumber.getText()));

                        //serialize the result to save it in the history
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        String timestampSting = timestamp.toString().replaceAll("[\\s.:]", "-");
                        Serialize.serialize(result, timestampSting);

                        //update resultLabel to display the result
                        //resultLabel.setText("Result: " + (calcResult = result.result) + " " + secondCurrency.getSelectedItem());

                        //timeout to show buttonanimation
                        TimeUnit.SECONDS.sleep(2);
                        //notify MyFrame to update
                        panelReloadListenerMyFrame.reloadPanel((calcResult = result.result), Objects.requireNonNull(secondCurrency.getSelectedItem()).toString());


                        //System.out.println(calcResult + " " + secondCurrency.getSelectedItem());

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (NumberFormatException exception) {
                        resultLabel.setText("No input given");
                        submitButton.setIcon(new ImageIcon("media/pixel-art-game-currency-coin-vector.png"));
                        throw new NumberFormatException();
                    }

                    TimeUnit.SECONDS.sleep(2);
                    return null;

                }

                @Override
                protected void done(){
                    submitButton.setIcon(new ImageIcon("media/pixel-art-game-currency-coin-vector.png"));
                }
            }.execute();



        }

    }

    public double getCalcResult() {
        return calcResult;
    }



}
