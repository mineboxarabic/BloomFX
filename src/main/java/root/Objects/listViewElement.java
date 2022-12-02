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
    Boolean isSelected;
    listViewElement lastElement;
    Task currenTask;
    Label titleTask;
    Label dateTask;
    Parent root;
    public void setLastElement(listViewElement element){
        lastElement = element;
    }
     public Task getTask(){
         return currenTask;
     }
     public void setTask(Task task){
         currenTask = task;
     }
     public void showDetails(Task task){
        if(lastElement != null && lastElement != this){
            lastElement.setStyle("-fx-background-color: #ffffff; -fx-cursor: hand;");
        }
        Parent roote = root;
        Scene scene = roote.getScene();
        Label titleDetail = (Label) scene.lookup("#titleDetail");
        Label descDetail = (Label) scene.lookup("#descDetail");
        titleDetail.setText(task.getTitle());
        descDetail.setText(task.getDescription());
        this.setStyle("-fx-background-color: #E0E0E0; -fx-cursor: hand; -fx-border-color: #000000; -fx-font-weight: bold;");
     }
     public void updateElement(Task task){
            currenTask = task;
            titleTask.setText(task.getTitle());
            dateTask.setText(task.getDate().toString());
            showDetails(task);
            setOnMouseClicked((event) ->showDetails(task));
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