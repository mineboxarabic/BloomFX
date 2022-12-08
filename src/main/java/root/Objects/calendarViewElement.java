package root.Objects;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
public class calendarViewElement extends VBox
{
    public Boolean isDragging = false;
    Parent root;
    public static calendarViewElement selected = null;
    public Task currentTask;
    public Button priorityTaskColor;
    public Label titleTask;
    public Label dateTask;
    public CheckBox checkBoxTask;
    
    public calendarViewElement(Task task) throws Exception{
        isDragging = false;
        setStyle("-fx-background-color: white;");
        currentTask = task;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("uiFXML/CalendarViewElement.fxml"));
        root = fxmlLoader.load();
        priorityTaskColor = (Button) root.lookup("#priorityTaskColor");
        titleTask = (Label) root.lookup("#titleTask");
        checkBoxTask = (CheckBox) root.lookup("#checkBoxTask");
        
        titleTask.setText(currentTask.getTitle());
        checkBoxTask.setSelected(currentTask.getStatus());
        switch(priorityTaskColor.getText()){
            case "Low":
                priorityTaskColor.setStyle("-fx-background-color: #00FF00");
                break;
            case "Medium":
                priorityTaskColor.setStyle("-fx-background-color: #FFFF00");
                break;
            case "High":
                priorityTaskColor.setStyle("-fx-background-color: #FF0000");
                break;
        }
        setOnMouseClicked((e) -> {
            if(e.getButton() == MouseButton.PRIMARY){
                if(!isDragging){
                    isDragging = true;
                    selected = this;
                    setStyle("-fx-background-color: gray; -fx-cursor: hand; -fx-border-color: black; -fx-border-width: 2px;");
                }
                else{
                    isDragging = false;
                    selected = null;
                    setStyle("-fx-background-color: #ffffff;");
                }
            }
        });
        this.getChildren().add(root);


    }
}
