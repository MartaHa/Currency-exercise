package pl.Marta.CurrencyApi;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class CalcOnCurrencies {

    /* Get number of days beetween the start and the end date */

    protected int getNumberOfDays(LocalDate startDate, LocalDate endDate) {
        Period period = Period.between(startDate, endDate);
        int days = period.getDays();
        return days;
    }

    /* counting average buying price */

    protected double averageBuyingPrice(String answer, int days) {


        double sumOfPrices = 0;


        /* creating json array from the String Api */

        JSONObject answerObject = new JSONObject(answer);

        /* Array for Rates */

        JSONArray ratesArray = answerObject.getJSONArray("rates");

        /*Array for days */

        for (int i = 0; i < ratesArray.length(); i++) {

            JSONObject day = ratesArray.getJSONObject(i);
            //Store the JSON object rates in JSON array as objects (in order to get from level 2 element bid)
            double currencyPrice = day.getDouble("bid");
            sumOfPrices += currencyPrice;
        }

        double averageBuyingPrice = sumOfPrices / days;
        return averageBuyingPrice;
    }


    /* counting standard sell deviation */

    protected double standardSellDeviation(String answer, int days) {


        double sumOfSellingPrices = 0;
        List<Double> listOfSellingPrices = new ArrayList<>();


        /* creating json array from the String Api */

        JSONObject jasonObject = new JSONObject(answer);
        JSONArray jaArr = (JSONArray) jasonObject.get("rates");


        for (int i = 0; i < jaArr.length(); i++) {

            JSONObject jsonobj_1 = (JSONObject) jaArr.get(i);

            //Store the JSON object rates in JSON array as objects (in order to get from level 2 element bid)
            double currencySellPrice = jsonobj_1.getDouble("ask");
            listOfSellingPrices.add(currencySellPrice);
            sumOfSellingPrices += currencySellPrice;
        }

        double averageSellingPrice = sumOfSellingPrices / days;
        double temporarySum = 0;

        for (int i = 0; i < listOfSellingPrices.size(); i++) {
            temporarySum += Math.pow((listOfSellingPrices.get(i) - averageSellingPrice), 2);
        }
        double variancy = temporarySum / days;
        double standardSellDeviation = Math.sqrt(variancy);
        return standardSellDeviation;
    }

}
