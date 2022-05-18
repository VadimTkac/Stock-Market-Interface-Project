import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class StockModelTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

    @Test
    //my testGetStock1 test
    public void testFirstURLConnection() 
    {
        StockModel stock = new StockModel();
        assertEquals(true, stock.getStock("nflx"));
    }
    
    @Test
    public void testAllCapsInput() 
    {
        StockModel stock = new StockModel();
        assertEquals(true, stock.getStock("TSLA"));
    }
    
    @Test
    public void testNumberSymbolInput() 
    {
        StockModel stock = new StockModel();
        assertEquals(false, stock.getStock("123"));
    }
    
    @Test
    //my testGetStock2 test
    public void testInvalidUserInput() 
    {
        StockModel stock = new StockModel();
        assertEquals(false, stock.getStock("abcdef"));
    }
    
    @Test
    //testing connection to graph api
    public void testGraphApiURLConnection() 
    {
        StockModel graph = new StockModel();
        assertEquals(true, graph.getStockGraph("jwn"));
    }
    
    @Test
    public void testGraphMethodAllCapsInput() 
    {
        StockModel graph = new StockModel();
        assertEquals(true, graph.getStockGraph("VOO"));
    }
    
    @Test
    public void testGraphMethodNumberInput() 
    {
        StockModel graph = new StockModel();
        assertEquals(false, graph.getStockGraph("1234566"));
    }
    
    @Test
    public void testGraphMethodInvalidInput() 
    {
        StockModel stock = new StockModel();
        assertEquals(false, stock.getStockGraph("abfcdef"));
    }           
    
}
