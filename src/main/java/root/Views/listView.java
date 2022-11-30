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
import javafx.scene.control.Label;
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
    void setData(Task tasks){
        this.task = tasks;
    }
    private void addTask() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("uiFXML/Popup.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        TextField title = (TextField) scene.lookup("#title");
        
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
