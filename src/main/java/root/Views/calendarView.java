package root.Views;

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
        node.setOnMousePressed(e -> {
            isDragging = true;
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
            node.setCursor(javafx.scene.Cursor.MOVE);
        });
        node.setOnMouseDragged(e -> {
            node.setTranslateX(e.getSceneX() - xOffset);
            node.setTranslateY(e.getSceneY() - yOffset);
            
        });

        node.setOnMouseReleased(e -> {
            node.setCursor(javafx.scene.Cursor.DEFAULT);
            isDragging = false;
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
                System.out.println("Node is " + day);


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
            monDayContainer.getChildren().add(element);
            makeDragable(element);
            selected = element;

        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
}
