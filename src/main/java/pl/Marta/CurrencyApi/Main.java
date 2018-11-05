package pl.Marta.CurrencyApi;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {


        /* Getting the information from the User */

        String currency;
        LocalDate startDate;
        LocalDate endDate;


        Scanner scanner = new Scanner(System.in);


        System.out.println("Please enter the Currency: ");
        currency = scanner.next();

        System.out.println("Please enter the start date: ");
        startDate = LocalDate.parse(scanner.next());

        System.out.println("Please enter the end date: ");
        endDate = LocalDate.parse(scanner.next());


       JSon json = new JSon();
        String answer = json.getInformationFromNBPApi(currency, startDate, endDate);



        CalcOnCurrencies calculations = new CalcOnCurrencies();

        /* Calculating number of days beetwen the dates */

        int days = calculations.getNumberOfDays(startDate, endDate);

        /* Calculating the average buying course */

        System.out.println("The average buying course is: " +calculations.averageBuyingPrice(answer,days));
//        System.out.println(calculations.standardSellDeviation(answer,days));
    }
}
