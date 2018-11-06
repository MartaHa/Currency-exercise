package pl.Marta.CurrencyApi;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {


    LocalDate startDate = null;
    LocalDate endDate = null;
    String currency = null;


    Scanner scanner = new Scanner(System.in);

    /* Getting the currency info */

    protected String getCurrency() {

        String line = "";
        while (currency == null) {
            System.out.println("Please enter the Currency symbol (EUR, USD): ");
            try {
                line = scanner.next();
                if (line.matches("[A-Z]{3}")) {
                    currency = line;
                } else {
                    System.out.println("That is not a proper format. Please enter the currency symbol ");
                    line = scanner.next();
                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }

        }
        return currency;
    }
    /* the start date */


    protected LocalDate getStartDate() {
        String lineDate = "";
        while (startDate == null) {
            System.out.println("Please enter the start date in format yyyy-mm-dd: ");
            try {
                lineDate = scanner.next();
                if (lineDate.matches("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))")) {
                    startDate = LocalDate.parse(lineDate);
                } else {
                    System.out.println("That is not a proper format. Please enter the  the start date in format yyy-mm-dd: ");
                    lineDate = scanner.next();
                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }

        }

        return startDate;
    }

    /* the end date */

    protected LocalDate getEndDate() {

        String lineDate2 = "";
        while (endDate == null) {
            System.out.println("Please enter the end date in format yyyy-mm-dd: ");
            try {
                lineDate2 = scanner.next();
                if (lineDate2.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                    endDate = LocalDate.parse(lineDate2);
                } else {
                    System.out.println("That is not a proper format. Please enter the  the start date in format yyy-mm-dd: ");
                    lineDate2 = scanner.next();
                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }
        }
        return endDate;
    }

}


