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

public class StockModel {

    //API 1 for the stock data
    // You must register for your own access token.
    private final String API_KEY = "17fc3175femsh07dddd4b17f5b5bp14877ejsn81b611b97ab8";
    private JsonElement jse;
    
    //API 2 for the chart
    private final String API_KEYX = "17fc3175femsh07dddd4b17f5b5bp14877ejsn81b611b97ab8";
    private JsonElement jsex;
    
    // first api connection test
    public boolean getStock(String stockSymb) {

        try {

            // Construct YH Finance API URL

            URL yhFinanceURL = new URL("https://yh-finance.p.rapidapi.com/market/v2/get-quotes?region=US&symbols=" 
                + stockSymb );
                
            URLConnection urlCon = yhFinanceURL.openConnection();
            
            // neccessary headers for the url connection
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
            System.out.println("ERROR: Something went wrong with the connection");
        }
            
        // check to see if the url and stock ticker was valid
        return isValid();
    }
    
    
    //2ND API connection
    public boolean getStockGraph(String stockSymb) {

        try {

            // Construct YH Finance Chart API URL

            URL yhFinanceGraphURL = new URL("https://yh-finance.p.rapidapi.com/stock/v3/get-chart?interval=1d&symbol=" 
                                            + stockSymb + "&range=3mo&region=US&includePrePost=false&useYfid=true&includeAdjustedClose=true&events=capitalGain,div,split");
            //making a url connection   
            URLConnection urlCon = yhFinanceGraphURL.openConnection();
             
            // neccessary headers for the url connection    
            urlCon.setRequestProperty("X-RapidAPI-Host", "yh-finance.p.rapidapi.com");
            urlCon.setRequestProperty("X-RapidAPI-Key", API_KEYX);
            
            // Open the URL Connection

            BufferedReader br = new BufferedReader(new InputStreamReader (urlCon.getInputStream()));   
            
            // Read the result into a JSON Element
            jsex = new JsonParser().parse(br);

            // Close the connection so we could pull data out later
            br.close();          
              
        } catch (java.io.UnsupportedEncodingException uee) {
            uee.printStackTrace();
        } catch (java.net.MalformedURLException mue) {
            mue.printStackTrace();
        } catch (java.io.IOException ioe) {
            System.out.println("ERROR: The Stock Symbol You Have Entered is not Valid");
        }
            
        // check to see if the url and stock ticker was valid
        return isValidX();
    }
        
    public boolean isValid() {
            
        // if the stock ticker is valid we will get an error field in the JSON
        try {
            String error = jse.getAsJsonObject().get("quoteResponse")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("region").getAsString();
            return true;
            
        } catch (Exception e) {
            
            // no err description so this is a stock symbol
            return false;
            
        }
    }
    
        public boolean isValidX() {
            
        // if the stock ticker is valid we will get an error field in the JSON
        try {
            String error = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(0).getAsString();
            return true;
            
        } catch (Exception e) {
            
            // no err description so this is a stock symbol
            return false;

        }
    }

    public String getOpenPrice() {
        
        String openF = "$%.2f"; 
            
        String open = jse.getAsJsonObject().get("quoteResponse")
                  .getAsJsonObject().get("result")
                  .getAsJsonArray().get(0)
                  .getAsJsonObject().get("regularMarketOpen").getAsString();
                  
        float openP = Float.parseFloat(open);
        
        String openPrice = String.format(openF, openP);
        
        return openPrice;
                      
    }
            
    public String getClosePrice() {
        
        String closeF = "$%.2f";
            
        String close = jse.getAsJsonObject().get("quoteResponse")
                          .getAsJsonObject().get("result")
                          .getAsJsonArray().get(0)
                          .getAsJsonObject().get("regularMarketPreviousClose").getAsString();
                  
        float closeP = Float.parseFloat(close);
        
        String closePrice = String.format(closeF, closeP);
        
        return closePrice;
                      
                  
                      
    }
            
    public String getHigh() {

                  
        String highF = "$%.2f";
            
        String high = jse.getAsJsonObject().get("quoteResponse")
                          .getAsJsonObject().get("result")
                          .getAsJsonArray().get(0)
                          .getAsJsonObject().get("regularMarketDayHigh").getAsString();
                          
        float highP = Float.parseFloat(high);
        
        String highPrice = String.format(highF, highP);
        
        return highPrice;
                      
    }
            
    public String getLow() {

                  
        String lowF = "$%.2f";
            
        String low = jse.getAsJsonObject().get("quoteResponse")
                          .getAsJsonObject().get("result")
                          .getAsJsonArray().get(0)
                          .getAsJsonObject().get("regularMarketDayLow").getAsString();
                          
        float lowP = Float.parseFloat(low);
        
        String lowPrice = String.format(lowF, lowP);
        
        return lowPrice;
                        
                      
    }
            
    public String getPE() {

        try {
                  
        String peF = "%.2f";
            
        String pe = jse.getAsJsonObject().get("quoteResponse")
                          .getAsJsonObject().get("result")
                          .getAsJsonArray().get(0)
                          .getAsJsonObject().get("trailingPE").getAsString();
                          
        float peP = Float.parseFloat(pe);
        
        String peR = String.format(peF, peP);
        
        return peR;  
              
        } catch (Exception e) {
            return "null";
        }
                      
    }
            
    public String getMktCap() {    
        
        try { 
                  
            String capF = "$%,.0f";
            
            String cap = jse.getAsJsonObject().get("quoteResponse")
                          .getAsJsonObject().get("result")
                          .getAsJsonArray().get(0)
                          .getAsJsonObject().get("marketCap").getAsString();
                          
            float capP = Float.parseFloat(cap);
        
            String mktCap = String.format(capF, capP);
        
            return mktCap;   
              
        } catch (Exception e) {
            return "null";
        }
        
    }
            
    public String getDivYield() {  
        
        try { 
                  
        String divF = "%.2f";
            
        String div = jse.getAsJsonObject().get("quoteResponse")
                          .getAsJsonObject().get("result")
                          .getAsJsonArray().get(0)
                          .getAsJsonObject().get("dividendYield").getAsString();
                          
        float divP = Float.parseFloat(div);
        
        String divYield = String.format(divF, divP);
        
        return divYield + "%";      
              
        } catch (Exception e) {
            return "0.00%";
        }
        
    }
            
    public String get52WkHigh() {

        try {
                  
            String highF = "$%.2f";
            
            String high = jse.getAsJsonObject().get("quoteResponse")
                          .getAsJsonObject().get("result")
                          .getAsJsonArray().get(0)
                          .getAsJsonObject().get("fiftyTwoWeekHigh").getAsString();
                          
            float highP = Float.parseFloat(high);
        
            String wkHigh = String.format(highF, highP);
        
            return wkHigh;
                      
        } catch (Exception e) {
            return "null";
        }  
                      
    }
    
    public String get52WkLow() {
                  
        try {
                  
            String lowF = "$%.2f";
            
            String low = jse.getAsJsonObject().get("quoteResponse")
                          .getAsJsonObject().get("result")
                          .getAsJsonArray().get(0)
                          .getAsJsonObject().get("fiftyTwoWeekLow").getAsString();
                          
            float lowP = Float.parseFloat(low);
        
            String wkLow = String.format(lowF, lowP);
        
            return wkLow; 
                              
        } catch (Exception e) {
            return "null";
        } 
                      
    }
    
    public String getVol() { 
        
        try {
                  
            String avgVolF = "%,.0f";
            
            String avgVol = jse.getAsJsonObject().get("quoteResponse")
                        .getAsJsonObject().get("result")
                        .getAsJsonArray().get(0)
                        .getAsJsonObject().get("averageDailyVolume3Month").getAsString(); 
                          
            float avgVolP = Float.parseFloat(avgVol);
        
            String avgVolR = String.format(avgVolF, avgVolP);
        
            return avgVolR;
                      
        } catch (Exception e) {
            return "null";
        }
                      
    }
    
    public String getAvgVol() {
        
        try {
                  
            String avgVolF = "%,.0f";
            
            String avgVol = jse.getAsJsonObject().get("quoteResponse")
                        .getAsJsonObject().get("result")
                        .getAsJsonArray().get(0)
                        .getAsJsonObject().get("averageDailyVolume3Month").getAsString(); 
                          
            float avgVolP = Float.parseFloat(avgVol);
            
            String avgVolR = String.format(avgVolF, avgVolP);
        
            return avgVolR;
                      
        } catch (Exception e) {
            return "null";
        }
                      
    }
    
    public String getEPS() {
        
        try {
                  
            String epsF = "%,.2f";
            
            String eps = jse.getAsJsonObject().get("quoteResponse")
                          .getAsJsonObject().get("result")
                          .getAsJsonArray().get(0)
                          .getAsJsonObject().get("epsTrailingTwelveMonths").getAsString();
                          
            float epsP = Float.parseFloat(eps);
        
            String epsR = String.format(epsF, epsP);
        
            return epsR; 
              
        } catch (Exception e) {
            return "null";
        }
                      
    }
            
    public Image getBadImage() {
            
                
            String picURL = "invalidsymbol.png"; 
                  
            return new Image(picURL);
                
        }
        
    public String getGraphData1() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(0).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData2() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(1).getAsString();        
        return data;
                      
    }
    
    public String getGraphData3() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(2).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData4() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(3).getAsString();        
        return data;
                      
    }
    
    public String getGraphData5() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(4).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData6() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(5).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData7() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(6).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData8() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(7).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData9() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(8).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData10() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(9).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData11() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(10).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData12() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(11).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData13() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(12).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData14() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(13).getAsString();        
        return data;
                      
    }
    
    public String getGraphData15() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(14).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData16() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(15).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData17() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(16).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData18() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(17).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData19() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(18).getAsString();        
        return data;
                      
    }
    
    public String getGraphData20() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(19).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData21() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(20).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData22() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(21).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData23() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(22).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData24() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(23).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData25() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(24).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData26() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(25).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData27() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(26).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData28() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(27).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData29() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(28).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData30() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(29).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData31() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(30).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData32() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(31).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData33() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(32).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData34() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(33).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData35() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(34).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData36() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(35).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData37() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(36).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData38() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(37).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData39() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(38).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData40() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(39).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData41() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(40).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData42() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(41).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData43() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(42).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData44() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(43).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData45() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(44).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData46() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(45).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData47() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(46).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData48() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(47).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData49() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(48).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData50() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(49).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData51() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(50).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData52() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(51).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData53() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(52).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData54() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(53).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData55() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(54).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData56() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(55).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData57() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(56).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData58() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(57).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData59() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(58).getAsString();
        
        return data;
                      
    }
    
    public String getGraphData60() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(59).getAsString();
       
        return data;
                      
    }
    
    public String getGraphData61() {
            
        String data = jsex.getAsJsonObject().get("chart")
                             .getAsJsonObject().get("result")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("indicators")
                             .getAsJsonObject().get("quote")
                             .getAsJsonArray().get(0)
                             .getAsJsonObject().get("close")
                             .getAsJsonArray().get(60).getAsString();
        
        return data;
                      
    }
        
}