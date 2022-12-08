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
import javafx.fxml.*;
public class calendarView implements Initializable
{
    Task currentTask;
    Boolean isDragging = false;
    double xOffset = 0;
    double yOffset = 0;
    /*public void getInfo(Parent day) throws Exception{
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
    }*/
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
    void getInfo(VBox day) throws IOException{
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
            Window window = day.getScene().getWindow();
            popup.show(window, window.getX() + window.getWidth() / 2, window.getY() + window.getHeight() / 2);
            
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
                        ContextMenu contextMenu = new ContextMenu();
                        MenuItem addTask = new MenuItem("Add Task");

                        addTask.setOnAction(e2 -> {
                            Vector<String> categories = new Vector<>();
                            try{
                                getInfo(theDay);
                                //categories.add("test");
                                //Task task = new Task("test", "test", LocalDate.now(), "fe",1,false,categories);
                                //addTask(task, theDay);
                            }
                            catch(Exception ex){
                                System.out.println(ex);
                            }
                            

                        });
                        contextMenu.getItems().add(addTask);
                        contextMenu.show(day, e.getScreenX(), e.getScreenY());

                     }
                });
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    void addTask(Task task,VBox day)
    {
        try{
            calendarViewElement element = new calendarViewElement(task);
            calendarViewElement.selected = element;
            day.getChildren().add(element);

        }
        catch(Exception e){
            System.out.println(e);
        }
    
    }
    @FXML
    void addTask(){
        Vector<String> categories = new Vector<>();
        categories.add("test");
        Task task = new Task("test", "test", LocalDate.now(), "fe",1,false,categories);
        addTask(task, sunDayContainer);
    }
    
}
