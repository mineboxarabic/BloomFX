package root.Views;

import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Vector;

import org.json.JSONArray;

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
import org.json.*;
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
    Vector<calendarViewElement> elements  = new Vector<calendarViewElement>();
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
    @FXML
    VBox tuesDayContainer;
    @FXML
    VBox wednesDayContainer;
    @FXML
    VBox thursDayContainer;
    @FXML
    VBox friDayContainer;
    @FXML
    VBox saturDayContainer;
    @FXML
    VBox categoriesContainer;
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
                for (Node task : categoriesEdited.getChildren())
                {
                    CheckBox category = (CheckBox) task;
                    if (category.isSelected()) 
                    {
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
            } catch (Exception ex) 
            {
                System.out.println(ex);
            }

        });
        cancelButton.setOnMouseClicked(e -> {
            popup.hide();
        });
        closeButton.setOnMouseClicked(e -> {
            popup.hide();
        });
        popup.show(theDay.getScene().getWindow());

    }
    
    void update(){
        for(calendarViewElement element : elements){
            VBox parent = (VBox) element.getParent();
            parent.getChildren().remove(element);
            parent.getChildren().add(element);
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        readFile();
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
            for(Node node : categoriesContainer.getChildren())
            {
                if(node instanceof CheckBox)
                {
                    CheckBox box = (CheckBox) node;
                    Vector<String> categories = new Vector<String>();
                    box.setOnMouseClicked(e -> {
                        if(box.isSelected())
                        {
                            categories.add(box.getText());
                            
                            for(calendarViewElement element : elements)
                            {
                                for(String category : categories){
                                if(element.getTask().getCategory().contains(category))
                                {
                                    element.setVisible(true);
                                }
                                }
                                
                                
                                
                            }
                        }
                        else
                        {
                            for(calendarViewElement element : elements)
                            {
                                if(element.getTask().getCategory().contains(box.getText()))
                                {
                                    element.setVisible(false);
                                }
                            }
                        }
                        //Sort elements in parent by visibility
                        update();
                    });
                    
                }
            }
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
            elements.add(element);

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
    public String getJson(String url)
    {
        String json = "";
        try{
            File file = new File(url);
            FileReader reader = new FileReader(file);
            int i;
            while((i = reader.read()) != -1){
                json += (char)i;
            }
            reader.close();
        }
        catch(IOException e){
            System.out.println("Error: " + e);
        }
        return json;
    }
    void readFile(){
        try{
            String json = getJson("src/main/java/root/Saves/save.json");
            JSONObject obj = new JSONObject(json);
            JSONArray arr = obj.getJSONArray("Tasks");
            
            for(int i = 0; i < arr.length(); i++){
                JSONObject task = arr.getJSONObject(i);
                String title = task.getString("title");
                String desc = task.getString("description");
                LocalDate date = LocalDate.parse(task.getString("Date")) ;
                String time = task.getString("Time");
                int priority = task.getInt("Priority");
                JSONArray categoriesArr = task.getJSONArray("Category");

                Vector<String> categories = new Vector<String>();
                for(int j = 0; j < categoriesArr.length(); j++){
                    categories.add(categoriesArr.getString(j));
                }
                Task t = new Task(title, desc, date, time, priority,categories);
                if(t.getDate().getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                    addTask(t, sunDayContainer);
                }
                else if(t.getDate().getDayOfWeek().equals(DayOfWeek.MONDAY)){
                    addTask(t, monDayContainer);
                }
                else if(t.getDate().getDayOfWeek().equals(DayOfWeek.TUESDAY)){
                    addTask(t, tuesDayContainer);
                }
                else if(t.getDate().getDayOfWeek().equals(DayOfWeek.WEDNESDAY)){
                    addTask(t, wednesDayContainer);
                }
                else if(t.getDate().getDayOfWeek().equals(DayOfWeek.THURSDAY)){
                    addTask(t, thursDayContainer);
                }
                else if(t.getDate().getDayOfWeek().equals(DayOfWeek.FRIDAY)){
                    addTask(t, friDayContainer);
                }
                else if(t.getDate().getDayOfWeek().equals(DayOfWeek.SATURDAY)){
                    addTask(t, saturDayContainer);
                }
                
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
        JSONObject obj = new JSONObject();
    }
    void writeFile(){
        try{
            JSONObject obj = new JSONObject();
            JSONArray arr = new JSONArray();
            JSONArray categories = new JSONArray();
            for(calendarViewElement element : elements){
                JSONObject task = new JSONObject();
                task.put("title", element.getTask().getTitle());
                task.put("description", element.getTask().getDescription());
                task.put("Date", element.getTask().getDate().toString());
                task.put("Time", element.getTask().getTime());
                task.put("Priority", element.getTask().getPriority());
                categories = new JSONArray();
                for(String category : element.getTask().getCategory())
                {
                    categories.put(category);
                }
                task.put("Category", categories);
                task.put("Status", element.getTask().getStatus());
                arr.put(task);
            }
            obj.put("Tasks", arr);
            FileWriter file = new FileWriter("src/main/java/root/Saves/save.json");
            file.write(obj.toString());
            file.close();
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
    }
}
