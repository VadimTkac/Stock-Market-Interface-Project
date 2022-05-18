import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCombination;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;

public class StockMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        try {
        
            Parent root = FXMLLoader.load(getClass().getResource("StockView.fxml"));
            Scene scene = new Scene(root);

            stage.setTitle("Stock Quote Final Project Vadim Tkachuk");

            stage.setScene(scene);
            stage.show();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
  
    }
    
    public static void main(String[] args) {
    
        launch(args);
    
    }

}