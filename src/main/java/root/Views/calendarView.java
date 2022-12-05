package root.Views;

import java.io.Console;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import root.Objects.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.*;
public class calendarView implements Initializable
{
    calendarViewElement selected;
    Boolean isDragging = false;
    double xOffset = 0;
    double yOffset = 0;
    @FXML
    HBox dayContainer;
    public void makeDragable(Node node){
        node.setOnMousePressed((e) -> {
            if(e.getButton() == MouseButton.PRIMARY){
                xOffset = e.getSceneX();
                yOffset = e.getSceneY();
                VBox parent = (VBox) node.getParent();
                parent.getChildren().remove(node);
                dayContainer.getChildren().add(node);
                isDragging = true;
            }
        });
        node.setOnMouseDragged((e) -> {
            if(e.getButton() == MouseButton.PRIMARY){
                node.setTranslateX(e.getSceneX() - xOffset);
                node.setTranslateY(e.getSceneY() - yOffset);
            }
        });


    }
    @FXML
    Button saveFileButton;
    @FXML
    public VBox sunDayContainer;
    @FXML
    public VBox monDayContainer;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try{
            /*sunDayContainer.setOnMouseClicked((event) ->{
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
                System.out.println(day.getId());
                day.setOnMouseEntered((event) ->{
                    if(isDragging){
                        day.setStyle("-fx-background-color: gray");
                    }
                });
                day.setOnMouseExited((event) ->{
                    if(isDragging){
                        day.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000");
                    }
                });
                /*day.setOnMouseClicked((event) ->{
                        if(isDragging){
                            System.out.println("Clicked");
                            //isDragging = false;
                            VBox parent = (VBox)selected.getParent();
                            parent.getChildren().remove(selected);
                            day.getChildren().add(selected);
                            return;
                        }
                });*/


        }}
        catch(Exception e){
            System.out.println(e);
        }

    }

    void addTask(Task task){
        
    }


    @FXML
    void addTask(){
        try{
            calendarViewElement element = new calendarViewElement();
            /*element.setOnMouseClicked((event) ->{
                if(event.getButton() == MouseButton.PRIMARY){
                    
                    try{
                        if(!isDragging){
                            selected = element;
                            isDragging = true;
                            System.out.println(isDragging);
                            selected.setStyle("-fx-background-color: #ff0000;");
                        }
                        else{
                            isDragging = false;
                            selected.setStyle("-fx-background-color: #ffffff;");
                            selected = null;
                            System.out.println(isDragging);

                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                    
                }
            });*/
            monDayContainer.getChildren().add(element);
            makeDragable(element);
            selected = element;

        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
}
