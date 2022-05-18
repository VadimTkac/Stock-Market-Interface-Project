import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.net.URL;
import java.util.HashMap;  
import java.util.Map;
import java.util.ArrayList;  
import java.util.Date;
import java.time.Instant;
import javafx.scene.image.Image;
import com.google.gson.*;
import java.net.URLConnection;
import java.io.*;  
import java.net.*; 

public class Stock
{
    // You must register for your own access token.
    private final String API_KEY = "17fc3175femsh07dddd4b17f5b5bp14877ejsn81b611b97ab8";

    public String getReport(String stockSymb, String range) {

        JsonElement jse = null;
        String stockReport = null;


        try {

            // Construct YH Finance API URL

            URL yhFinanceGraphURL = new URL("https://yh-finance.p.rapidapi.com/stock/v2/get-chart?interval=1d&symbol="
                + stockSymb
                + "&range=1y&region=US");
                
            URLConnection urlCon = yhFinanceGraphURL.openConnection();
                
            urlCon.setRequestProperty("X-RapidAPI-Host", "yh-finance.p.rapidapi.com");
            urlCon.setRequestProperty("X-RapidAPI-Key", API_KEY);
            

            // Open the URL Connection

            BufferedReader br = new BufferedReader(new InputStreamReader (urlCon.getInputStream()));   
            
            // Read the result into a JSON Element
            jse = new JsonParser().parse(br);


            // Close the connection
            br.close();            
        } catch (java.io.UnsupportedEncodingException uee) {
            uee.printStackTrace();
        } catch (java.net.MalformedURLException mue) {
            mue.printStackTrace();
        } catch (java.io.IOException ioe) {
            System.out.println("ERROR: No cities match your search query");
        }

        if (jse != null) {

            System.out.println(jse);
            
            stockReport = "hello";
            // Formatting the data
            
            String locFormat = "%-15s %1s %n";
            String timeFormat = "%-15s %1s %n";
            String weatherFormat = "%-15s %1s %n";
            String tempFormat = "%-15s %1s %n";
            String windFormat = "%-15s %1s %n";
            String degFormat = "%-15s %1s %n";
            String presFormat = "%-15s %.2f %n";
            
            String test = jse.getAsJsonObject().get("quoteResponse")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("region").getAsString();
                             
                             
                             //.getAsJsonArray().get("region").getAsString(); 
            System.out.print(test);
            
            // Build a definition report
            
            /*
            String location = jse.getAsJsonObject().get("name").getAsString();
            stockReport = String.format(locFormat, "Location:", location);
            
            String time = jse.getAsJsonObject().get("dt").getAsString();
            java.util.Date date = new java.util.Date((long)(Long.parseLong(time))*1000);
            
            stockReport = stockReport + String.format(timeFormat, "Time:", date);
            
            String weather = jse.getAsJsonObject().get("weather")
                              .getAsJsonArray().get(0)
                              .getAsJsonObject().get("description").getAsString();
            stockReport = stockReport + String.format(weatherFormat, "Weather:", weather);
            
            String temp = jse.getAsJsonObject().get("main")
                            .getAsJsonObject().get("temp").getAsString();
            stockReport = stockReport + String.format(tempFormat, "Temperature F:", temp);
            
            String windSpd = jse.getAsJsonObject().get("wind")
                            .getAsJsonObject().get("speed").getAsString();
            stockReport = stockReport + String.format(windFormat, "Wind:", windSpd + " MPH");
            
            String windDir = jse.getAsJsonObject().get("wind")
                    .getAsJsonObject().get("deg").getAsString();
            stockReport = stockReport + String.format(degFormat, "Wind direction:", windDir + " degrees");

            String pressure = jse.getAsJsonObject().get("main")
                    .getAsJsonObject().get("pressure").getAsString();
            double pres = (Double.parseDouble(pressure) / 33.864);
            stockReport = stockReport + String.format(presFormat, "Pressure in HG:", pres);
          */  
        }
        
        return stockReport;
    }

    public static void main (String[]args)
    {
        Stock stockSummary = new Stock();

        String stockSummaryReport = stockSummary.getReport(args[0], args[1]);
        
        if (args.length != 0 && stockSummaryReport != null)
        {
           
            System.out.print(stockSummaryReport);
        }
        
    }

}