package root.Objects;
import javafx.fxml.*;

import java.io.IOException;
import java.time.LocalDate;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
public class listViewElement extends VBox{
     String title;
     String description;
     LocalDate date;
     String time;
     int priority;
     boolean status;
     String category;
     int id;
     Task currenTask;
     Label titleTask;
     Label dateTask;
     Parent root;
     public Task getTask(){
         return currenTask;
     }
     public void setTask(Task task){
         currenTask = task;
     }
     public void updateElement(listViewElement focuesedElement,Scene newScene){
            Task task = focuesedElement.getTask();
            currenTask = task;
            Parent roote = root;
            titleTask = (Label) roote.lookup("#titleTask");
            dateTask = (Label) roote.lookup("#dateTask");
            titleTask.setText(task.getTitle());
            dateTask.setText(task.getDate().toString());

            setOnMouseClicked((event) ->extracted(focuesedElement, task, newScene));
     }
    private void extracted(listViewElement focuesedElement, Task task, Scene newScene) {
        try{
            //get listView.fxml from src/main/resources/root/listView.fxml
            Scene scene = newScene;
            Label titleDetail = (Label) scene.lookup("#titleDetail");
            Label descDetail = (Label) scene.lookup("#descDetail");
            if(focuesedElement != null){
                focuesedElement.setStyle("-fx-background-color: white;");
            }
            titleDetail.setText(task.getTitle());
            descDetail.setText(task.getDescription());
            focuesedElement = this;
            focuesedElement.setStyle("-fx-background-color: gray;");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public listViewElement(Task task) throws IOException{
        currenTask = task;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("uiFXML/listViewElement.fxml"));
        root = fxmlLoader.load();
        Rectangle priorityTaskColor = (Rectangle) root.lookup("#priorityTaskColor");
        CheckBox checkBoxTask = (CheckBox) root.lookup("#checkBoxTask");
        titleTask = (Label) root.lookup("#titleTask");
        dateTask = (Label) root.lookup("#dateTask");
        setStyle("-fx-Cursor: hand;");
        titleTask.setText(task.getTitle());
        dateTask.setText(task.getDate().toString());
        switch(task.getPriority()){
            case 0:
                priorityTaskColor.setStyle("-fx-fill: #00FF00");
                break;
            case 1:
                priorityTaskColor.setStyle("-fx-fill: #FFFF00");
                break;
            case 2:
                priorityTaskColor.setStyle("-fx-fill: #FF0000");
                break;
        }
        setId("listViewElements");
        getChildren().add(root);
    }
}