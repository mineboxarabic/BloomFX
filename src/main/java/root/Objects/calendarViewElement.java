package root.Objects;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
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
    public Label descTask;
    public CheckBox checkBoxTask;
    public Task getTask(){
        return currentTask;
    }
    void taskEdited(){
        Popup popup = new Popup();
        VBox content = new VBox();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("uiFXML/Popupv2.fxml"));
            Parent root = loader.load();
            content.getChildren().add(root);
            Scene scene = new Scene(content);
            Button saveButton = (Button) scene.lookup("#saveEditedButton");
            Button cancelButton = (Button) scene.lookup("#cancelEditedButton");
            TextField titleField = (TextField) scene.lookup("#titleEdited");
            TextArea DescArea = (TextArea) scene.lookup("#descEdited");
            DatePicker datePicker = (DatePicker) scene.lookup("#dateEdited");
            ComboBox<String> priorityComboBox = (ComboBox) scene.lookup("#PriorityEdited");
            Button closeButton = (Button) root.lookup("#closeButton");
            priorityComboBox.getItems().addAll("Low", "Medium", "High");
            titleField.setText(currentTask.getTitle());
            DescArea.setText(currentTask.getDescription());
            datePicker.setValue(currentTask.getDate());
            priorityComboBox.getSelectionModel().select(currentTask.getPriority());
            closeButton.setOnMouseClicked(e -> {
                popup.hide();
            });
            saveButton.setOnMouseClicked(e -> {
                currentTask.setTitle(titleField.getText());
                currentTask.setDescription(DescArea.getText());
                currentTask.setPriority(priorityComboBox.getSelectionModel().getSelectedIndex());
                currentTask.setDate(datePicker.getValue());
                titleTask.setText(currentTask.getTitle());
                descTask.setText(currentTask.getDescription());
                int priority = currentTask.getPriority();
                switch(priority){
                    case -1:
                        priorityTaskColor.setStyle("-fx-background-color: gray");
                        break;
                    case 0:
                        priorityTaskColor.setStyle("-fx-background-color: #00FF00");
                        break;
                    case 1:
                        priorityTaskColor.setStyle("-fx-background-color: #FFFF00");
                        break;
                    case 2:
                        priorityTaskColor.setStyle("-fx-background-color: #FF0000");
                        break;
                }
                popup.hide();
            });
            cancelButton.setOnMouseClicked(e -> {
                popup.hide();
            });
            popup.getContent().add(scene.getRoot());
            popup.show(this.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public calendarViewElement(Task task) throws Exception{
        isDragging = false;
        setStyle("-fx-background-color: white; -fx-cursor: hand;");
        currentTask = task;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("uiFXML/CalendarViewElement.fxml"));
        root = fxmlLoader.load();
        priorityTaskColor = (Button) root.lookup("#priorityTaskColor");
        titleTask = (Label) root.lookup("#titleTask");
        checkBoxTask = (CheckBox) root.lookup("#checkBoxTask");
        descTask = (Label) root.lookup("#descTask");
        descTask.setText(currentTask.getDescription());
        titleTask.setText(currentTask.getTitle());
        checkBoxTask.setSelected(currentTask.getStatus());
        int priority = currentTask.getPriority();
        switch(priority){
            case -1:
                priorityTaskColor.setStyle("-fx-background-color: gray");
                break;
            case 0:
                priorityTaskColor.setStyle("-fx-background-color: #00FF00");
                break;
            case 1:
                priorityTaskColor.setStyle("-fx-background-color: #FFFF00");
                break;
            case 2:
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
            else {
                ContextMenu contextMenu = new ContextMenu();
                MenuItem edit = new MenuItem("Edit");
                MenuItem delete = new MenuItem("Delete");
                edit.setOnAction((e1) -> {
                    taskEdited();
                });
                delete.setOnAction((e1) -> {
                    this.getParent().getChildrenUnmodifiable().remove(this);
                });
                contextMenu.getItems().addAll(edit, delete);
                contextMenu.show(this, e.getScreenX(), e.getScreenY());

            }
        });
        this.getChildren().add(root);



    }
}
