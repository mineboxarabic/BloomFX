package root.Views;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Vector;

import root.Objects.Task;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import org.json.*;
import javafx.event.*;

import root.Objects.dayViewElement;



public class dayView implements Initializable
{
    Vector<dayViewElement> elements = new Vector<dayViewElement>();
    Vector<dayViewElement> deletedElements = new Vector<dayViewElement>();
    Vector<dayViewElement> completedElements = new Vector<dayViewElement>();
    @FXML
    TextField titleTaskField;
    @FXML
    Button addTaskButton;
    @FXML
    VBox taskTodayContainer;
    @FXML
    VBox taskTomorrowContainer;
    @FXML
    ComboBox<String> priorityComboBox;
    @FXML
    VBox taskWeekContainer;
    @FXML
    VBox longTimeTaskContainer;
    @FXML
    VBox categoriesContainer;
    @FXML
    Button showCompletedButton;
    @FXML
    Button showTrashButton;
    @FXML
    Button saveFileButton;
    public void addTask(Task task){
        try{
            
            dayViewElement element = new dayViewElement(task);
            
            element.removeButton.setOnAction(e -> {
                VBox parent = (VBox) element.getParent();
                deletedElements.add(element);
                elements.remove(element);
                parent.getChildren().remove(element);
                
            });
            //Si on change la date du task;
            element.dateTask.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                   for(dayViewElement e : elements){
                    try{
                    VBox parent = (VBox) e.getParent();
                    int tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();
                    int today = LocalDate.now().getDayOfMonth();
                    int elementDay = e.getTask().getDate().getDayOfMonth();
                    int nextWeek = LocalDate.now().plusDays(7).getDayOfMonth();
                    if(e.getTask().getDate().getDayOfMonth() == tomorrow){
                        parent.getChildren().remove(e);
                        taskTomorrowContainer.getChildren().add(e);
                    }
                    else if (elementDay > tomorrow && elementDay < nextWeek){
                        parent.getChildren().remove(e);
                        taskWeekContainer.getChildren().add(e);
                    }
                    else if (elementDay > nextWeek){
                        parent.getChildren().remove(e);
                        longTimeTaskContainer.getChildren().add(e);
                    }
                    else if (elementDay == today){
                        parent.getChildren().remove(e);
                        taskTodayContainer.getChildren().add(e);
                    } 
                    }catch(Exception ex){
                        System.out.println(ex);
                    }
                    
                }
            }}); 
            element.checkBoxTask.setOnMouseClicked((event) ->{
                if(element.checkBoxTask.isSelected()){
                    completedElements.add(element);
                    element.getTask().setStatus(true);
                    //Add line through titleTask
                    element.titleTask.setStyle("-fx-text-fill: #c0c0c0; -fx-text-decoration: line-through;");
                    element.setStyle("-fx-background-color: #ffffff; -fx-opacity: 0.5; -fx-text-decoration: line-through;");
                }
                else{
                    completedElements.remove(element);
                    element.getTask().setStatus(false);
                    element.titleTask.setStyle("-fx-text-fill: #000000;");
                    element.setStyle("-fx-background-color: #ffffff; -fx-cursor: hand;");
                }
                
            });

            showCompletedButton.setOnAction(e -> {
                PopupShowSelected popup = new PopupShowSelected(completedElements);
            });
            showTrashButton.setOnAction(e -> {
                PopupShowSelected popup = new PopupShowSelected(deletedElements);
            });
            taskTodayContainer.getChildren().add(element);
            titleTaskField.setText("");
            elements.add(element);

        System.out.println("Added task");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    @FXML
    public void addTask(){
        Vector<String> categories = new Vector<String>();
        Task task = new Task(titleTaskField.getText(), " ", LocalDate.now(), "12:00", priorityComboBox.getSelectionModel().getSelectedIndex(), categories);
        addTask(task);
    }
    public void initialize(URL url, ResourceBundle rb)
    {
        readFile();
        saveFileButton.setOnAction(e -> {
            writeFile();
        });
        priorityComboBox.getItems().addAll("High", "Medium", "Low");
        for(Node node : categoriesContainer.getChildren())
            {
                if(node instanceof CheckBox)
                {
                    CheckBox box = (CheckBox) node;
                    box.setOnMouseClicked(e -> {
                        if(box.isSelected())
                        {
                            for(dayViewElement element : elements)
                            {
                                if(element.getTask().getCategory().contains(box.getText()))
                                {
                                    element.setVisible(true);
                                }
                                
                            }
                        }
                        else
                        {
                            for(dayViewElement element : elements)
                            {
                                if(element.getTask().getCategory().contains(box.getText()))
                                {
                                    element.setVisible(false);
                                }
                            }
                        }
                        //Sort elements in parent by visibility
                        
                    });
                }
            }
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
                addTask(t);
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
            for(dayViewElement element : elements){
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