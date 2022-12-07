package root.Objects;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
public class calendarViewElement extends VBox
{
    public Boolean isDragging = false;
    public static calendarViewElement selected;
    public Task currentTask;
    public Button priorityTaskColor;
    public Label titleTask;
    public Label dateTask;
    public CheckBox checkBoxTask;
    
    public calendarViewElement(Task task) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("uiFXML/CalendarViewElement.fxml"));

        currentTask = task;
        Parent root = loader.load();
        try{
            priorityTaskColor = (Button) root.lookup("#priorityTaskColor");
            checkBoxTask = (CheckBox) root.lookup("#checkBoxTask");
            titleTask = (Label) root.lookup("#titleTask");
            
            titleTask.setText(currentTask.getTitle());
        switch(task.getPriority())
        {
            case -1:
                priorityTaskColor.setStyle("-fx-background-color: gray;");
                break;
            case 0:
                priorityTaskColor.setStyle("-fx-background-color: #FF0000");
                break;
            case 1:
                priorityTaskColor.setStyle("-fx-background-color: #FFFF00");
                break;
            case 2:
                priorityTaskColor.setStyle("-fx-background-color: #00FF00");
                break;
            
        }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        
        getChildren().add(root);
        setOnMouseClicked((event) ->{
            if(event.getButton() == MouseButton.PRIMARY){
                try{
                    if(!isDragging){
                        selected = this;
                        isDragging = true;
                        selected.setStyle("-fx-background-color: gray;");
                    }
                    else{
                        isDragging = false;
                        selected.setStyle("-fx-background-color: #ffffff;");
                        selected = null;
                    }
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
            else{
                try{
                    Popup popup = new Popup();
                    VBox content = new VBox();
                    Button close = new Button("Close");
                    close.setOnAction((e) ->{
                        popup.hide();
                    });
                    content.getChildren().add(close);

                    content.setStyle("-fx-background-color: #ffffff;");
                    content.setPrefSize(200, 200);
                    content.getChildren().add(new Label("Hello World"));
                    popup.getContent().add(content);
                    popup.show(this, event.getScreenX(), event.getScreenY());
                }
                catch(Exception e){
                    System.out.println(e);
                }
                
            }
        });

        


    }
}
