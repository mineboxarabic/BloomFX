package root.Views;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.nio.file.Path;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import root.Objects.*;
public class listView
{
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
        Popup popup = new Popup();
        popup.save.setOnAction(e -> {
            System.out.println("Adding task");
            Task task = popup.getTask();
            task = popup.getTask();
            System.out.println(task.getTitle());
            System.out.println(task.getDescription());
            System.out.println(task.getDate());
            System.out.println(task.getPriority());
        });
        
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
