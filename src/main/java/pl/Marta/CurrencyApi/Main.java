package pl.Marta.CurrencyApi;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {

        /* geting data from user */

        UserInput userInput = new UserInput();

        String currency = userInput.getCurrency();
        LocalDate startDate = userInput.getStartDate();
        LocalDate endDate = userInput.getEndDate();

        /* getting the connection and data from NBP API */

        JSon json = new JSon();
        String answer = json.getInformationFromNBPApi(currency, startDate, endDate);


        CalcOnCurrencies calculations = new CalcOnCurrencies();

        /* Calculating number of days beetwen the dates */

        int days = calculations.getNumberOfDays(startDate, endDate);
        System.out.println(days);

        /* Calculating the average buying course */

        System.out.println("The average buying course is: " + calculations.averageBuyingPrice(answer, days));
        System.out.println("The standard deviation for selling currency is: " + calculations.standardSellDeviation(answer, days));
    }
}
