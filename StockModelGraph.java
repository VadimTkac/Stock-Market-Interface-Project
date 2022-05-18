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

public class StockModelGraph {

    // You must register for your own access token.
    private final String API_KEY = "17fc3175femsh07dddd4b17f5b5bp14877ejsn81b611b97ab8";
    private JsonElement jse;

    public boolean getStockGraph(String stockSymb) {

        try {

            // Construct YH Finance API URL

            URL yhFinanceGraphURL = new URL("https://yh-finance.p.rapidapi.com/stock/v3/get-chart?interval=1d&symbol=" 
                                            + stockSymb + "&range=1mo&region=US&includePrePost=false&useYfid=true&include
                                            AdjustedClose=true&events=capitalGain,div,split");
                
            URLConnection urlCon = yhFinanceGraphURL.openConnection();
                
            urlCon.setRequestProperty("X-RapidAPI-Host", "yh-finance.p.rapidapi.com");
            urlCon.setRequestProperty("X-RapidAPI-Key", API_KEY);
            

            // Open the URL Connection

            BufferedReader br = new BufferedReader(new InputStreamReader (urlCon.getInputStream()));   
            
            // Read the result into a JSON Element
            jse = new JsonParser().parse(br);
            System.out.println(jse);


            // Close the connection
            br.close();          
              
        } catch (java.io.UnsupportedEncodingException uee) {
            uee.printStackTrace();
        } catch (java.net.MalformedURLException mue) {
            mue.printStackTrace();
        } catch (java.io.IOException ioe) {
            System.out.println("ERROR: No cities match your zip code");
        }
            
            // check to see if the stock ticker was valid
        return isValid();
    }
        
    public boolean isValid() {
            
        // if the stock ticker is valid we will get an error field in the JSON
        try {
            String error = jse.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("timestamp")
                             .getAsJsonArray().get(0).getAsString();
            return true;
            
        } catch (java.lang.NullPointerException npe) {
            
            // no err description so this is a stock symbol
            return false;
            
        }
    }

    public String getGraphData() {
        
        System.out.println(jse);
        String error = jse.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(0).getAsString();
                             
        return error;
                      
    }

}