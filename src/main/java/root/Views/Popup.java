package root.Views;
import java.io.IOException;

import root.Objects.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class Popup {
    Task task;
    public Button save;
    public Popup() throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("uiFXML/Popup.fxml"));
            Parent root =(Parent) fxmlLoader.load();
            
            Stage stage = new Stage();
            stage.setTitle("Popup");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            
            save = (Button) scene.lookup("#saveButton");
            Button cancelButton = (Button) scene.lookup("#cancelButton");
            TextField titleField = (TextField) scene.lookup("#titleField");
            TextArea DescArea = (TextArea) scene.lookup("#DescArea");
            DatePicker datePicker = (DatePicker) scene.lookup("#datePicker");
            ComboBox<String> priorityComboBox = (ComboBox<String>) scene.lookup("#priorityComboBox");
            priorityComboBox.getItems().addAll("Low", "Medium", "High");

            stage.show();
            save.setOnAction(e -> {
                task = new Task(titleField.getText(), DescArea.getText(), datePicker.getValue(), "1:2", priorityComboBox.getSelectionModel().getSelectedIndex(), "Test");
                System.out.println("Saving");
                stage.close();
            });
            
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    public Task getTask(){
        return task;
    }
    
}
