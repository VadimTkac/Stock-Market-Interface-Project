import java.awt.event.ActionListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.chart.*;

/*
 * @author Vadim Tkachuk
 */

public class StockController implements Initializable {

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private Button btnSearch;
    
    @FXML
    private TextField txtStockSymb;
    
    @FXML
    private TextField txtOpenPrice;
    
    @FXML
    private Label lblOpenPrice;
    
    @FXML
    private TextField txtClosePrice;
    
    @FXML
    private Label lblClosePrice;
    
    @FXML
    private TextField txtHigh;
    
    @FXML
    private Label lblHigh;
    
    @FXML
    private TextField txtLow;
    
    @FXML
    private Label lblLow;
    
    @FXML
    private TextField txtPE;
    
    @FXML
    private Label lblPE;
    
    @FXML
    private TextField txtDivYield;
    
    @FXML
    private Label lblDivYield;
    
    @FXML
    private TextField txt52WkLow;
    
    @FXML
    private Label lbl52WkLow;
    
    @FXML
    private TextField txt52WkHigh;
    
    @FXML
    private Label lbl52WkHigh;
    
    @FXML
    private TextField txtVol;
    
    @FXML
    private Label lblVol;
    
    @FXML
    private TextField txtAvgVol;
    
    @FXML
    private Label lblAvgVol;
    
    @FXML
    private TextField txtMktCap;
    
    @FXML
    private Label lblMktCap;
    
    @FXML
    private TextField txtEPS;
    
    @FXML
    private Label lblEPS;
    
    @FXML
    private ImageView badStockSymbImg;
    
    @FXML
    private void handleButtonAction(ActionEvent e) {
        //create object access to the StockModel
        StockModel stockAccess = new StockModel();
        
        
    // has the search button been pressed?
        if (e.getSource() == btnSearch) {
            String stockSymb = txtStockSymb.getText();
            try {
                //checking both api connections
                if ((stockAccess.getStock(stockSymb)) == true && (stockAccess.getStockGraph(stockSymb)) == true) {

                    txtOpenPrice.setText(String.valueOf(stockAccess.getOpenPrice()));
                    txtClosePrice.setText(String.valueOf(stockAccess.getClosePrice()));
                    txtHigh.setText(String.valueOf(stockAccess.getHigh()));
                    txtLow.setText(stockAccess.getLow());
                    txtPE.setText(String.valueOf(stockAccess.getPE()));
                    txtDivYield.setText(stockAccess.getDivYield());
                    txt52WkHigh.setText(String.valueOf(stockAccess.get52WkHigh()));
                    txt52WkLow.setText(String.valueOf(stockAccess.get52WkLow()));
                    txtVol.setText(String.valueOf(stockAccess.getVol()));
                    txtAvgVol.setText(String.valueOf(stockAccess.getAvgVol()));
                    txtMktCap.setText(String.valueOf(stockAccess.getMktCap()));
                    txtEPS.setText(stockAccess.getEPS());
                    badStockSymbImg.setImage(null);

                    lineChart.getData().clear();
                    XYChart.Series<String, Number> data = new XYChart.Series<String, Number>();
                    data.setName("1 Day Interval");
                    double data1 = (Double.parseDouble(stockAccess.getGraphData1())); 
                    data.getData().add(new XYChart.Data<String, Number>("0", data1));
                    double data2 = (Double.parseDouble(stockAccess.getGraphData2())); 
                    data.getData().add(new XYChart.Data<String, Number>("1", data2));
                    double data3 = (Double.parseDouble(stockAccess.getGraphData2())); 
                    data.getData().add(new XYChart.Data<String, Number>("2", data3));
                    double data4 = (Double.parseDouble(stockAccess.getGraphData4())); 
                    data.getData().add(new XYChart.Data<String, Number>("3", data4));
                    double data5 = (Double.parseDouble(stockAccess.getGraphData5())); 
                    data.getData().add(new XYChart.Data<String, Number>("4", data5));
                    double data6 = (Double.parseDouble(stockAccess.getGraphData6())); 
                    data.getData().add(new XYChart.Data<String, Number>("5", data6));
                    double data7 = (Double.parseDouble(stockAccess.getGraphData7())); 
                    data.getData().add(new XYChart.Data<String, Number>("6", data7));
                    double data8 = (Double.parseDouble(stockAccess.getGraphData8())); 
                    data.getData().add(new XYChart.Data<String, Number>("7", data8));
                    double data9 = (Double.parseDouble(stockAccess.getGraphData9())); 
                    data.getData().add(new XYChart.Data<String, Number>("8", data9));
                    double data10 = (Double.parseDouble(stockAccess.getGraphData10())); 
                    data.getData().add(new XYChart.Data<String, Number>("9", data10));
                    double data11 = (Double.parseDouble(stockAccess.getGraphData11())); 
                    data.getData().add(new XYChart.Data<String, Number>("10", data11));
                    double data12 = (Double.parseDouble(stockAccess.getGraphData12())); 
                    data.getData().add(new XYChart.Data<String, Number>("11", data12));
                    double data13 = (Double.parseDouble(stockAccess.getGraphData13())); 
                    data.getData().add(new XYChart.Data<String, Number>("12", data13));
                    double data14 = (Double.parseDouble(stockAccess.getGraphData14())); 
                    data.getData().add(new XYChart.Data<String, Number>("13", data14));
                    double data15 = (Double.parseDouble(stockAccess.getGraphData15())); 
                    data.getData().add(new XYChart.Data<String, Number>("14", data15));
                    double data16 = (Double.parseDouble(stockAccess.getGraphData16())); 
                    data.getData().add(new XYChart.Data<String, Number>("15", data16));
                    double data17 = (Double.parseDouble(stockAccess.getGraphData17())); 
                    data.getData().add(new XYChart.Data<String, Number>("16", data17));
                    double data18 = (Double.parseDouble(stockAccess.getGraphData18())); 
                    data.getData().add(new XYChart.Data<String, Number>("17", data18));
                    double data19 = (Double.parseDouble(stockAccess.getGraphData19())); 
                    data.getData().add(new XYChart.Data<String, Number>("18", data19));
                    double data20 = (Double.parseDouble(stockAccess.getGraphData20())); 
                    data.getData().add(new XYChart.Data<String, Number>("19", data20));
                    double data21 = (Double.parseDouble(stockAccess.getGraphData21())); 
                    data.getData().add(new XYChart.Data<String, Number>("20", data21));
                    double data22 = (Double.parseDouble(stockAccess.getGraphData22())); 
                    data.getData().add(new XYChart.Data<String, Number>("21", data22));
                    double data23 = (Double.parseDouble(stockAccess.getGraphData23())); 
                    data.getData().add(new XYChart.Data<String, Number>("22", data23));
                    double data24 = (Double.parseDouble(stockAccess.getGraphData24())); 
                    data.getData().add(new XYChart.Data<String, Number>("23", data24));
                    double data25 = (Double.parseDouble(stockAccess.getGraphData25())); 
                    data.getData().add(new XYChart.Data<String, Number>("24", data25));
                    double data26 = (Double.parseDouble(stockAccess.getGraphData26())); 
                    data.getData().add(new XYChart.Data<String, Number>("25", data26));
                    double data27 = (Double.parseDouble(stockAccess.getGraphData27())); 
                    data.getData().add(new XYChart.Data<String, Number>("26", data27));
                    double data28 = (Double.parseDouble(stockAccess.getGraphData28())); 
                    data.getData().add(new XYChart.Data<String, Number>("27", data28));
                    double data29 = (Double.parseDouble(stockAccess.getGraphData29())); 
                    data.getData().add(new XYChart.Data<String, Number>("28", data29));
                    double data30 = (Double.parseDouble(stockAccess.getGraphData30())); 
                    data.getData().add(new XYChart.Data<String, Number>("29", data30));
                    double data31 = (Double.parseDouble(stockAccess.getGraphData31())); 
                    data.getData().add(new XYChart.Data<String, Number>("30", data31));
                    double data32 = (Double.parseDouble(stockAccess.getGraphData32())); 
                    data.getData().add(new XYChart.Data<String, Number>("31", data32));
                    double data33 = (Double.parseDouble(stockAccess.getGraphData33())); 
                    data.getData().add(new XYChart.Data<String, Number>("32", data33));
                    double data34 = (Double.parseDouble(stockAccess.getGraphData34())); 
                    data.getData().add(new XYChart.Data<String, Number>("33", data34));
                    double data35 = (Double.parseDouble(stockAccess.getGraphData35())); 
                    data.getData().add(new XYChart.Data<String, Number>("34", data35));
                    double data36 = (Double.parseDouble(stockAccess.getGraphData36())); 
                    data.getData().add(new XYChart.Data<String, Number>("35", data36));
                    double data37 = (Double.parseDouble(stockAccess.getGraphData37())); 
                    data.getData().add(new XYChart.Data<String, Number>("36", data37));
                    double data38 = (Double.parseDouble(stockAccess.getGraphData38())); 
                    data.getData().add(new XYChart.Data<String, Number>("37", data38));
                    double data39 = (Double.parseDouble(stockAccess.getGraphData39())); 
                    data.getData().add(new XYChart.Data<String, Number>("38", data39));
                    double data40 = (Double.parseDouble(stockAccess.getGraphData40())); 
                    data.getData().add(new XYChart.Data<String, Number>("39", data40));
                    double data41 = (Double.parseDouble(stockAccess.getGraphData41())); 
                    data.getData().add(new XYChart.Data<String, Number>("40", data41));
                    double data42 = (Double.parseDouble(stockAccess.getGraphData42())); 
                    data.getData().add(new XYChart.Data<String, Number>("41", data42));
                    double data43 = (Double.parseDouble(stockAccess.getGraphData43())); 
                    data.getData().add(new XYChart.Data<String, Number>("42", data43));
                    double data44 = (Double.parseDouble(stockAccess.getGraphData44())); 
                    data.getData().add(new XYChart.Data<String, Number>("43", data44));
                    double data45 = (Double.parseDouble(stockAccess.getGraphData45())); 
                    data.getData().add(new XYChart.Data<String, Number>("44", data45));
                    double data46 = (Double.parseDouble(stockAccess.getGraphData46())); 
                    data.getData().add(new XYChart.Data<String, Number>("45", data46));
                    double data47 = (Double.parseDouble(stockAccess.getGraphData47())); 
                    data.getData().add(new XYChart.Data<String, Number>("46", data47));
                    double data48 = (Double.parseDouble(stockAccess.getGraphData48())); 
                    data.getData().add(new XYChart.Data<String, Number>("47", data48));
                    double data49 = (Double.parseDouble(stockAccess.getGraphData49())); 
                    data.getData().add(new XYChart.Data<String, Number>("48", data49));
                    double data50 = (Double.parseDouble(stockAccess.getGraphData50())); 
                    data.getData().add(new XYChart.Data<String, Number>("49", data50));
                    double data51 = (Double.parseDouble(stockAccess.getGraphData51())); 
                    data.getData().add(new XYChart.Data<String, Number>("50", data51));
                    double data52 = (Double.parseDouble(stockAccess.getGraphData52())); 
                    data.getData().add(new XYChart.Data<String, Number>("51", data52));
                    double data53 = (Double.parseDouble(stockAccess.getGraphData53())); 
                    data.getData().add(new XYChart.Data<String, Number>("52", data53));
                    double data54 = (Double.parseDouble(stockAccess.getGraphData54())); 
                    data.getData().add(new XYChart.Data<String, Number>("53", data54));
                    double data55 = (Double.parseDouble(stockAccess.getGraphData55())); 
                    data.getData().add(new XYChart.Data<String, Number>("54", data55));
                    double data56 = (Double.parseDouble(stockAccess.getGraphData56())); 
                    data.getData().add(new XYChart.Data<String, Number>("55", data56));
                    double data57 = (Double.parseDouble(stockAccess.getGraphData57())); 
                    data.getData().add(new XYChart.Data<String, Number>("56", data57));
                    double data58 = (Double.parseDouble(stockAccess.getGraphData58())); 
                    data.getData().add(new XYChart.Data<String, Number>("57", data58));
                    double data59 = (Double.parseDouble(stockAccess.getGraphData59())); 
                    data.getData().add(new XYChart.Data<String, Number>("58", data59));
                    double data60 = (Double.parseDouble(stockAccess.getGraphData60())); 
                    data.getData().add(new XYChart.Data<String, Number>("59", data60));
                    double data61 = (Double.parseDouble(stockAccess.getGraphData61())); 
                    data.getData().add(new XYChart.Data<String, Number>("60", data61));
                    
                    lineChart.getData().add(data);
                                            
                } else  {                                
                    txtStockSymb.setText("Enter Valid Symbol");
                    txtOpenPrice.setText("");
                    txtClosePrice.setText("");
                    txtHigh.setText("");
                    txtLow.setText("");
                    txtPE.setText("");
                    txtDivYield.setText("");
                    txt52WkHigh.setText("");
                    txt52WkLow.setText("");
                    txtVol.setText("");
                    txtAvgVol.setText("");
                    txtMktCap.setText("");
                    txtEPS.setText("");
                    badStockSymbImg.setImage(stockAccess.getBadImage());
                    lineChart.getData().clear();

                }
                
            } catch (Exception x) {
                
                System.out.println("Something went wrong!");
            
            }
       
        }
    
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }     
     
}  