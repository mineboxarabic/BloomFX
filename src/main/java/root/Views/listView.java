package root.Views;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Vector;

import javafx.fxml.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import root.Objects.*;
public class listView
{
    listViewElement focuesedElement;
    Vector<listViewElement> elements = new Vector<listViewElement>();
    @FXML
    Button addButton;
    @FXML
    Button removeButton;
    @FXML
    Button editButton;
    @FXML
    VBox taskContainer;
    @FXML
    VBox detailContainer;
    @FXML
    Label titleDetail;
    @FXML
    Label descDetail;
    void addNewTask(Task t)
    {
        try{
            Task task = new Task(t.getTitle(), t.getDescription(), t.getDate(), t.getTime(), t.getPriority(), t.getCategory());
            listViewElement element = new listViewElement(task);
            elements.add(element);
            taskContainer.getChildren().add(element);
            element.setOnMouseClicked((event) ->{
                try{
                    if(focuesedElement != null){
                        focuesedElement.setStyle("-fx-background-color: white;");
                    }
                    titleDetail.setText(task.getTitle());
                    descDetail.setText(task.getDescription());
                    focuesedElement = element;
                    focuesedElement.setStyle("-fx-background-color: gray;");
                }
                catch(Exception e){
                    System.out.println(e);
                }
                
            });
        }
        catch(IOException e){
            System.out.println("Error: " + e);
        }
        
    }
    void saveTask(String title, String description, LocalDate date, String time, int priority, String category){
            //focuesedElement.updateElement(element);
    }
    @FXML
    private void addTask() throws IOException {
        
        try
        {
        Popup popup = new Popup();
        Scene scene = popup.getScene();

        Button saveButton = (Button) scene.lookup("#saveButton");
        Button cancelButton = (Button) scene.lookup("#cancelButton");
        TextField titleField = (TextField) scene.lookup("#titleField");
        TextArea DescArea = (TextArea) scene.lookup("#DescArea");
        DatePicker datePicker = (DatePicker) scene.lookup("#datePicker");
        ComboBox<String> priorityComboBox = (ComboBox) scene.lookup("#priorityComboBox");
        priorityComboBox.getItems().addAll("Low", "Medium", "High");
        
        saveButton.setOnAction((e) ->{
            addNewTask(new Task(titleField.getText(), DescArea.getText(), datePicker.getValue(), "12:00", priorityComboBox.getSelectionModel().getSelectedIndex(), "Work"));
            popup.close();
        });
        cancelButton.setOnAction((e) ->{
            popup.close();
        });
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    @FXML
    private void removeTask() throws IOException {
        for(int i = 0; i < taskContainer.getChildren().size(); i++){
            if(taskContainer.getChildren().get(i) == focuesedElement){
                taskContainer.getChildren().remove(i);
            }
        }
    }
    @FXML
    private void editTask() throws Exception {
        Popup popup = new Popup(focuesedElement);
        popup.show();
    }


}
