package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Result {
    String firstCurrency;
    String secondCurrency;
    public double exchangeRate;
    public double amount;
    public double result;
    final transient String CONNECT_API_URL = "https://openexchangerates.org/api/latest.json";
    final transient String CONNECT_API_KEY = "f7b054e08f9045c68e3766fd2c461692";

    public Result(String firstCurrency, String secondCurrency, double amount) throws IOException {
        this.firstCurrency = firstCurrency;
        this.secondCurrency = secondCurrency;
        firstCurrency = firstCurrency.substring(0,3);
        secondCurrency = secondCurrency.substring(0,3);

        this.amount = amount;

        exchangeRate = setExchangeRate(firstCurrency,secondCurrency);
        result = calcResult(amount, exchangeRate);
        //System.out.println(this);
    }

    public double setExchangeRate(String firstCurrency, String secondCurrency) throws IOException {
        String urlString = CONNECT_API_URL + "?app_id=" + CONNECT_API_KEY + "&symbols=" + firstCurrency + "," + secondCurrency;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        connection.disconnect();
        //System.out.println(content);

        JSONObject jsonResponse = new JSONObject(content.toString());
        JSONObject rates = jsonResponse.getJSONObject("rates");
        double fromRate = rates.getDouble(firstCurrency);
        double toRate = rates.getDouble(secondCurrency);
        exchangeRate = fromRate / toRate;

        return exchangeRate;
    }

    public double calcResult(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }

    @Override
    public String toString() {
        return "Converting: " + firstCurrency + " to " + secondCurrency + ". With exchange Rate: " + exchangeRate + ". Result: " + result + " " + secondCurrency;
    }

    //to String to be called by the "deserialize" Methode -> Easier to display object in the HistoryPanel
    public String toStringDeserialize(){
        return firstCurrency + ";" + secondCurrency + ";" + amount + ";" + result;
    }
}
