package root.Views;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.nio.file.Path;

import javafx.event.Event;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import root.Objects.*;
public class listView
{
    Task task;
    @FXML
    Button addButton;
    @FXML
    Button removeButton;
    @FXML
    Button editButton;
    @FXML
    VBox taskContainer;
    @FXML
    private void addTask() throws IOException {
        Tasks tasks = new Tasks();
        try{
        /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("uiFXML/Popup.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
        Popup popup = new Popup();
        Scene scene = popup.getScene();

        Button saveButton = (Button) scene.lookup("#saveButton");
        Button cancelButton = (Button) scene.lookup("#cancelButton");
        TextField titleField = (TextField) scene.lookup("#titleField");
        TextArea DescArea = (TextArea) scene.lookup("#DescArea");
        DatePicker datePicker = (DatePicker) scene.lookup("#datePicker");
        ComboBox priorityComboBox = (ComboBox) scene.lookup("#priorityComboBox");
        priorityComboBox.getItems().addAll("Low", "Medium", "High");
        
        saveButton.setOnAction((e) ->{
            int priority = priorityComboBox.getSelectionModel().getSelectedIndex(); ;
            task = new Task(titleField.getText(), DescArea.getText(), datePicker.getValue(),"test",priority ,"Default");
            tasks.addTask(task);
            try{
                listViewElement element = new listViewElement(task);
                taskContainer.getChildren().add(element);
            }catch(IOException ex){
                System.out.println("Error");
            }
            popup.close();
        });
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        
    }
    @FXML
    private void removeTask() throws IOException {
        System.out.println("Removing task");
    }
    @FXML
    private void editTask() throws IOException {
        System.out.println("Editing task");
    }


}
