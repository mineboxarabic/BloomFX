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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import root.Objects.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.*;
public class calendarView implements Initializable
{
    calendarViewElement selected;
    Task currentTask;
    Boolean isDragging = false;
    double xOffset = 0;
    double yOffset = 0;
    public void getInfo(Parent day) throws Exception{
        Popup popup = new Popup();
        VBox content = new VBox();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("uiFXML/Popupv2.fxml"));
        Parent root = loader.load();
        content.getChildren().add(root);
        Scene scene = new Scene(content);
        Button saveButton = (Button) scene.lookup("#saveButton");
        Button cancelButton = (Button) scene.lookup("#cancelButton");
        TextField titleField = (TextField) scene.lookup("#titleField");
        TextArea DescArea = (TextArea) scene.lookup("#DescArea");
        DatePicker datePicker = (DatePicker) scene.lookup("#datePicker");
        ComboBox<String> priorityComboBox = (ComboBox) scene.lookup("#priorityComboBox");
        priorityComboBox.getItems().addAll("Low", "Medium", "High");

        datePicker.show();
        popup.show();
        saveButton.setOnMouseClicked(e -> {
            currentTask.setTitle(titleField.getText());
            currentTask.setDescription(DescArea.getText());
            currentTask.setPriority(priorityComboBox.getSelectionModel().getSelectedIndex());
            currentTask.setDate(datePicker.getValue());
            popup.hide();
        });
        cancelButton.setOnMouseClicked(e -> {
            popup.hide();
        });
    }
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
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try{
            /*sunDayContainer.sekkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkktOnMouseClicked((event) ->{
                if(event.getButton() == MouseButton.SECONDARY){
                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem addTask = new MenuItem("Add Task");
                    contextMenu.getItems().add(addTask);
                    contextMenu.show(sunDayContainer, event.getScreenX(), event.getScreenY());
                }
            });*/
            System.out.println( dayContainer.getChildren().size());
            for(Node node : dayContainer.getChildren()){
                VBox day = (VBox)node;
                day.setOnMouseEntered((event) ->{
                    if(selected.isDragging){
                        day.setStyle("-fx-background-color: lightgray ; -fx-border-color: #000000");
                    }
                });
                day.setOnMouseExited((event) ->{
                    if(selected.isDragging){
                        day.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000");
                    }
                });
                day.setOnMouseClicked((event) ->{
                    if(event.getButton() == MouseButton.SECONDARY){
                        ContextMenu contextMenu = new ContextMenu();
                        MenuItem addTask = new MenuItem("Add Task");
                        addTask.setOnAction((event2) ->{
                            try{
                                Vector<String> f = new Vector<String>();
                                Task t = new Task("New Task", "New Task Description", LocalDate.now(), "12:00", 1, false,f);
                                addTask(t,day);
                            }
                            catch(Exception e){
                                System.out.println(e);
                            }
                            
                        });
                        contextMenu.getItems().add(addTask);
                        contextMenu.show(day, event.getScreenX(), event.getScreenY());
                    }
                    else{
                        day.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000");
                        if(selected.isDragging){
                            System.out.println("Clicked");
                            VBox parent = (VBox)selected.getParent();
                            parent.getChildren().remove(selected);
                            day.getChildren().add(selected);
                            
                            return;
                        }
                    }
                    
                });
        }}
        catch(Exception e){
            System.out.println(e);
        }

    }

    void addTask(Task task,VBox day){
        try{
            Task t = task;
            calendarViewElement element = new calendarViewElement(t);
            day.getChildren().add(element);
            selected = element;
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


    @FXML
    void addTask(){
        
    }
    
}
