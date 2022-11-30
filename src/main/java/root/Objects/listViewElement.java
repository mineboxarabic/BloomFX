package root.Objects;
import javafx.fxml.*;

import java.io.IOException;
import java.time.LocalDate;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
        title = task.getTitle();
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
        getChildren().add(separator);
    }
}