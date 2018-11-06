package pl.Marta.CurrencyApi;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import java.util.ArrayList;
import java.util.List;

public class CalcOnCurrencies {

    /* Get number of days beetween the start and the end date */

    protected int getNumberOfDays(LocalDate startDate, LocalDate endDate) {
        Period period = Period.between(startDate, endDate);
        int days = period.getDays() + 1;
        return days;
    }

    /* counting average buying price */

    protected BigDecimal averageBuyingPrice(String answer, int days) {


        double sumOfPrices = 0;
        BigDecimal averageBuyingPriceRounded = null;
        double averageBuyingPrice = 0;

        try {

            /* creating json array from the String Api */
            JSONObject answerObject = new JSONObject(answer);

            /* Array for Rates */
            JSONArray ratesArray = answerObject.getJSONArray("rates");

            /*Array for days */
            for (int i = 0; i < ratesArray.length(); i++) {

                JSONObject day = ratesArray.getJSONObject(i);

                /* getting the cost and adding it to sum */
                double currencyPrice = day.getDouble("bid");
                sumOfPrices += currencyPrice;
            }

            /*counting average */
            try {
                averageBuyingPrice = sumOfPrices / days;
            } catch (ArithmeticException e) {
                System.out.println("You can't divide by 0");
            }
            averageBuyingPriceRounded = new BigDecimal(averageBuyingPrice).setScale(4, BigDecimal.ROUND_HALF_DOWN);
        } catch (
                JSONException e) {
            e.printStackTrace();
        }
        return averageBuyingPriceRounded;
    }


    /* counting standard sell deviation */

    protected BigDecimal standardSellDeviation(String answer, int days) {


        double sumOfSellingPrices = 0;
        BigDecimal standardSellDeviationRounded = null;
        List<Double> listOfSellingPrices = new ArrayList<>();
        double variancy = 0;
        double averageSellingPrice = 0;


        try {

            /* creating json array from the String Api */
            JSONObject answerObject = new JSONObject(answer);

            /* Array for Rates */
            JSONArray ratesArray = answerObject.getJSONArray("rates");

            /*Array for days */
            for (int i = 0; i < ratesArray.length(); i++) {

                JSONObject jsonobj_1 = (JSONObject) ratesArray.get(i);
                double currencySellPrice = jsonobj_1.getDouble("ask");
                listOfSellingPrices.add(currencySellPrice);
                sumOfSellingPrices += currencySellPrice;
            }
            try {
                averageSellingPrice = sumOfSellingPrices / days;
            } catch (ArithmeticException e) {
                System.out.println("You can't divide by 0");
            }
            double temporarySum = 0;


            for (int i = 0; i < listOfSellingPrices.size(); i++) {
                temporarySum += Math.pow((listOfSellingPrices.get(i) - averageSellingPrice), 2);
            }
            try {
                variancy = temporarySum / days;
            } catch (ArithmeticException e) {
                System.out.println("You can't divide by 0");
            }
            double standardSellDeviation = Math.sqrt(variancy);
            standardSellDeviationRounded = new BigDecimal(standardSellDeviation).setScale(4, BigDecimal.ROUND_HALF_DOWN);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return standardSellDeviationRounded;
    }
}
