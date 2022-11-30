package root.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Popup extends Stage
{
    Scene scene;
    public Popup() throws Exception
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("uiFXML/Popup.fxml"));
            Parent root = fxmlLoader.load();
            scene = new Scene(root);
            this.setScene(scene);
            this.show();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
   
}
