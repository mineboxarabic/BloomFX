package root.Views;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Vector;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;
import root.Objects.Task;
import root.Objects.listViewElement;
public class listView implements Initializable
{
    listViewElement currentElement;
    listViewElement lastElement;
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

    @Override
    public void initialize(URL url, ResourceBundle rb){
        readFile();
    }
    void addNewTask(Task t)
    {
        try{
            Task task = new Task(t.getTitle(), t.getDescription(), t.getDate(), t.getTime(), t.getPriority(), t.getCategory());
            listViewElement element = new listViewElement(task);
            elements.add(element);
            taskContainer.getChildren().add(element);
            element.setOnMouseClicked((event) ->{
                try{
                    currentElement = element;
                    currentElement.setLastElement(lastElement);
                    currentElement.showDetails(task);
                    lastElement = currentElement;
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
    //read a json file
        //read the json file and add the tasks to the list
        try{
            String json = getJson("src/main/java/root/Views/save.json");
            JSONObject obj = new JSONObject(json);
            JSONArray arr = obj.getJSONArray("Tasks");
            for(int i = 0; i < arr.length(); i++){
                JSONObject task = arr.getJSONObject(i);
                String title = task.getString("title");
                String desc = task.getString("description");
                LocalDate date = LocalDate.parse(task.getString("Date")) ;
                String time = task.getString("Time");
                int priority = task.getInt("Priority");
                String category = task.getString("Category");
                Vector<String> categories = new Vector<String>();
                categories.add(category);
                Task t = new Task(title, desc, date, time, priority, categories);
                addNewTask(t);
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
        JSONObject obj = new JSONObject();
    }
    @FXML
    private void addTask() throws IOException {
        
        try
        {
        Popups popup = new Popups();
        Scene scene = popup.getScene();
        Button saveButton = (Button) scene.lookup("#saveButton");
        Button cancelButton = (Button) scene.lookup("#cancelButton");
        TextField titleField = (TextField) scene.lookup("#titleField");
        TextArea DescArea = (TextArea) scene.lookup("#DescArea");
        DatePicker datePicker = (DatePicker) scene.lookup("#datePicker");
        ComboBox<String> priorityComboBox = (ComboBox) scene.lookup("#priorityComboBox");
        priorityComboBox.getItems().addAll("Low", "Medium", "High");
        
        saveButton.setOnAction((e) ->{
            Vector<String> categories = new Vector<String>();
                categories.add("Default");
            addNewTask(new Task(titleField.getText(), DescArea.getText(), datePicker.getValue(), "12:00", priorityComboBox.getSelectionModel().getSelectedIndex(), categories));
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
            if(taskContainer.getChildren().get(i) == currentElement){
                taskContainer.getChildren().remove(i);
            }
        }
    }
    @FXML
    private void editTask() throws Exception {
        Popups popup = new Popups(currentElement);
    }


}
