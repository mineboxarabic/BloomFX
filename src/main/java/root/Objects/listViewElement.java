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
    public listViewElement(Task task) throws IOException{
        /*title = task.getTitle();
        description = task.getDescription();
        date = task.getDate();
        priority = task.getPriority();
        status = false;
        category = task.getCategory();
        setMaxHeight(10);
        HBox hBox = new HBox();
        hBox.setPrefWidth(500);
        hBox.setPrefHeight(50);
        getChildren().add(hBox);
        hBox.getChildren().add(new CheckBox());
        hBox.getChildren().add(new Label(title));
        hBox.getChildren().add(new Label(date.toString()));
        hBox.getChildren().add(new Label(category));
        //Create a Horizontal Separator
        Separator separator = new Separator();
        getChildren().add(separator);*/
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("uiFXML/listViewElement.fxml"));
        Parent root = fxmlLoader.load();
        Rectangle priorityTaskColor = (Rectangle) root.lookup("#priorityTaskColor");
        CheckBox checkBoxTask = (CheckBox) root.lookup("#checkBoxTask");
        Label titleTask = (Label) root.lookup("#titleTask");
        Label dateTask = (Label) root.lookup("#dateTask");
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

        getChildren().add(root);
    }
}