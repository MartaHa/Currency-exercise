package pl.Marta.CurrencyApi;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Scanner;

public class JSon {


    /* Getting the currency information from NBP API */

    protected String getInformationFromNBPApi(String currency, LocalDate startDate, LocalDate endDate) throws IOException {

        String answer = "";

        String uri = "http://api.nbp.pl/api/exchangerates/rates/c/" + currency + "/" + startDate + "/" + endDate + "/";

        URL url = new URL(uri);

        //Parse URL into HttpURLConnection in order to open the connection in order to get the JSON data
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        //Set the request to GET or POST as per the requirements
        conn.setRequestMethod("GET");

        //Use the connect method to create the connection bridge
        conn.connect();

        //Get the response status of the Rest API
        int responsecode = conn.getResponseCode();
        System.out.println("Response code is: " + responsecode);

        //Iterating condition to if response code is not 200 then throw a runtime exception
        //else continue the actual process of getting the JSON data
        if (responsecode != 200)
            throw new RuntimeException("HttpResponseCode: " + responsecode);

        else {
            //Scanner functionality will read the JSON data from the stream
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) {
                answer += sc.nextLine();
            }
            System.out.println("\nJSON Response in String format");
            System.out.println(answer);
            //Close the stream when reading the data has been finished
            sc.close();
        }
        //Disconnect the HttpURLConnection stream
        conn.disconnect();

        return answer;
    }
}