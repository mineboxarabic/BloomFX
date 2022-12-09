package root.Views;

import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Window;
import root.Objects.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.*;

public class calendarView implements Initializable {
    Task currentTask;
    Boolean isDragging = false;
    double xOffset = 0;
    double yOffset = 0;
    Node dayEvent;
    ContextMenu contextMenu;
    @FXML
    VBox mainContainer;
    @FXML
    HBox dayContainer;
    @FXML
    Button saveFileButton;
    @FXML
    public VBox sunDayContainer;
    @FXML
    public VBox monDayContainer;

    void getInfo(VBox theDay) throws IOException {
        Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.setAutoFix(true);
        popup.setHideOnEscape(true);
        VBox content = new VBox();
        content.setStyle("-fx-background-color: #ffffff;");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("uiFXML/Popupv2.fxml"));
        Parent root = loader.load();
        Button saveButton = (Button) root.lookup("#saveEditedButton");
        Button cancelButton = (Button) root.lookup("#cancelEditedButton");
        TextField titleField = (TextField) root.lookup("#titleEdited");
        TextArea DescArea = (TextArea) root.lookup("#descEdited");
        DatePicker datePicker = (DatePicker) root.lookup("#dateEdited");
        ComboBox<String> priorityComboBox = (ComboBox) root.lookup("#PriorityEdited");
        HBox categoriesEdited = (HBox) root.lookup("#categoriesEdited");
        Button closeButton = (Button) root.lookup("#closeButton");
        priorityComboBox.getItems().addAll("Low", "Medium", "High");
        content.getChildren().add(root);
        popup.getContent().add(content);
        saveButton.setOnMouseClicked(e -> {
            try {
                Vector<String> categories = new Vector<String>();
                for (Node task : categoriesEdited.getChildren()) {
                    CheckBox category = (CheckBox) task;
                    if (category.isSelected()) {
                        categories.add(category.getText());
                    }
                }
                String title = titleField.getText();
                String description = DescArea.getText();
                System.out.println(description);
                int priority = priorityComboBox.getSelectionModel().getSelectedIndex();
                LocalDate date = datePicker.getValue();

                Task task = new Task(title, description, date, "12", priority, categories);
                addTask(task, theDay);
                popup.hide();
            } catch (Exception ex) {
                System.out.println(ex);
            }

        });
        cancelButton.setOnMouseClicked(e -> {
            popup.hide();
        });
        closeButton.setOnMouseClicked(e -> {
            popup.hide();
        });
        // get the mouse position
        popup.show(theDay.getScene().getWindow());

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try
        {
            for(Node day : dayContainer.getChildren())
            {
                VBox theDay = (VBox) day;
                theDay.setStyle("-fx-background-color: white; -fx-border-color: black;");
                day.setOnMouseEntered(e -> {
                    if(calendarViewElement.selected != null){
                        if(calendarViewElement.selected.isDragging){
                            theDay.setStyle("-fx-background-color: lightgray;-fx-border-color: black;");
                        }
                        else{
                            theDay.setStyle("-fx-background-color: white; -fx-border-color: black;");
                        }
                    }
                });
                day.setOnMouseExited(e -> {
                    theDay.setStyle("-fx-background-color: white; -fx-border-color: black;");
                    if(calendarViewElement.selected != null){
                        if(calendarViewElement.selected.isDragging){
                            theDay.setStyle("-fx-background-color: white;-fx-border-color: black;");
                        }
                        else{
                            
                        }
                        
                    }
                });
               day.setOnMouseClicked(e -> {
                     if(e.getButton() == MouseButton.PRIMARY)
                     {
                        try{
                        if(contextMenu != null)
                            contextMenu.hide();
                            if(calendarViewElement.selected != null){
                                if(calendarViewElement.selected.isDragging && !theDay.getChildren().contains(calendarViewElement.selected)){
                                    theDay.getChildren().add(calendarViewElement.selected);
                                }
                             }
                        }catch(Exception ex){
                            System.out.println(ex);
                        }
                         
                     }
                     else{
                        try{
                            dayEvent = (VBox) e.getTarget();
                            System.out.println(dayEvent.getId());
                        }catch(Exception ex){
                            dayEvent = (Label) e.getTarget();
                        }
                            if((dayEvent.getId().equals("sunDayContainer")) 
                            || (dayEvent.getId().equals("monDayContainer") 
                            || (dayEvent.getId().equals("tuesDayContainer")) 
                            || (dayEvent.getId().equals("wednesDayContainer")) 
                            || (dayEvent.getId().equals("thursDayContainer")) 
                            || (dayEvent.getId().equals("friDayContainer")) 
                            || (dayEvent.getId().equals("saturDayContainer")))){
                            if(contextMenu != null)
                                contextMenu.hide();
                            
                            contextMenu = new ContextMenu();
                            MenuItem addTask = new MenuItem("Add Task");

                            addTask.setOnAction(e2 -> {
                            
                                    Vector<String> categories = new Vector<>();
                                    try{
                                        getInfo(theDay);
                                    }
                                    catch(Exception ex){
                                        System.out.println(ex);
                                    }
                            });
                            contextMenu.getItems().add(addTask);
                            contextMenu.show(day, e.getScreenX(), e.getScreenY());
                        }
                        
                }
                
            }
            );
        }
    }
    catch(Exception e){
        System.out.println(e);
    }




    }

    void addTask(Task task, VBox day) {
        try {
            calendarViewElement element = new calendarViewElement(task);
            calendarViewElement.selected = element;
            day.getChildren().add(element);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    void addTask() {
        Vector<String> categories = new Vector<>();
        categories.add("test");
        Task task = new Task("test", "test", LocalDate.now(), "fe", 1, false, categories);
        addTask(task, sunDayContainer);
    }

}
