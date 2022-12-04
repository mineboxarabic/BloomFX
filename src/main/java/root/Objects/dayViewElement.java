package root.Objects;


import java.io.IOException;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class dayViewElement extends VBox
{
    Parent root;
    boolean isSelected;
    Task currentTask;
    static dayViewElement lastElement;
    Button editButton;
    public Button removeButton;
    Button priorityTaskColor;
    public Label titleTask;
    public Label dateTask;
    public CheckBox checkBoxTask;
    void showFiltered(){

    }
    void showDetails(){
        
        try{
            Parent dayView = this.getParent();
            Scene scene = dayView.getScene();
            Label titleDetail = (Label) scene.lookup("#titleDetail");
            Label descDetail = (Label) scene.lookup("#descDetail");
            titleDetail.setText(currentTask.getTitle());
            descDetail.setText(currentTask.getDescription());
        }catch(Exception e){
            System.out.println(e);
        }
        

    }
    public Task getTask(){
        return currentTask;
    }
    void editElement(){

    }
    public dayViewElement(Task task) throws IOException
    {
        currentTask = task;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("uiFXML/dayViewElement.fxml"));
        root = fxmlLoader.load();
        priorityTaskColor = (Button) root.lookup("#priorityTaskColor");
         checkBoxTask = (CheckBox) root.lookup("#checkBoxTask");
        HBox buttonsContainer = (HBox) root.lookup("#buttonsContainer");
         titleTask = (Label) root.lookup("#titleTask");
        dateTask = (Label) root.lookup("#dateTask");
        //setStyle("-fx-background-color: #ffffff; -fx-cursor: hand;");
                      
        editButton = new Button();
        removeButton = new Button();
        editButton.setId("editButton");
        removeButton.setId("removeButton");
        editButton.setPrefSize(40, 40);
        removeButton.setPrefSize(40, 40);
        buttonsContainer.getChildren().add(editButton);
        buttonsContainer.getChildren().add(removeButton);
        editButton.setVisible(false);
        removeButton.setVisible(false);
        setStyle("-fx-background-color: #ffffff; -fx-cursor: hand;");
        setOnMouseClicked((event) -> {
            if((!isSelected || lastElement == this) && currentTask.getStatus() == false){
                if(lastElement != null && lastElement != this){
                    lastElement.setStyle("-fx-background-color: #ffffff; -fx-cursor: hand;");
                    lastElement.isSelected = false;
                    lastElement.editButton.setVisible(false);
                    lastElement.removeButton.setVisible(false);
                    System.out.println(lastElement.getTask().getTitle());
                }
                showDetails();
                setStyle("-fx-background-color: #E0E0E0; -fx-cursor: hand; -fx-border-color: #000000");
                editButton.setVisible(true);
                removeButton.setVisible(true);
                //Edit button
                editButton.setOnAction((e) ->{
                    try {
                        FXMLLoader popup = new FXMLLoader(getClass().getResource("uiFXML/dayViewEditPopUp.fxml"));
                        Parent editedRoot = popup.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(editedRoot));
                        TextField titleEdited = (TextField) editedRoot.lookup("#titleEdited");
                        TextArea descEdited = (TextArea) editedRoot.lookup("#descEdited");
                        DatePicker dateEdited = (DatePicker) editedRoot.lookup("#dateEdited");
                        ComboBox<String> PriorityEdited = (ComboBox) editedRoot.lookup("#PriorityEdited");
                        HBox categoriesEdited = (HBox) editedRoot.lookup("#categoriesEdited");
                        Button saveEditedButton = (Button) editedRoot.lookup("#saveEditedButton");
                        Button cancelEditedButton = (Button) editedRoot.lookup("#cancelEditedButton");
                        
                        titleEdited.setText(currentTask.getTitle());
                        descEdited.setText(currentTask.getDescription());
                        dateEdited.setValue(currentTask.getDate());
                        for(Node node : categoriesEdited.getChildren()){
                            if(node instanceof CheckBox){
                                CheckBox checkBox = (CheckBox) node;
                                if(currentTask.getCategory().contains(checkBox.getText())){
                                    checkBox.setSelected(true);
                                }
                                
                            }
                        }
                        try{
                            PriorityEdited.getItems().addAll("Low", "Medium", "High");
                            PriorityEdited.setValue(PriorityEdited.getItems().get(currentTask.getPriority()));
                        }
                        catch(Exception ex){
                            System.out.println(ex);
                        }
                        
                        saveEditedButton.setOnAction((ev) -> {
                            currentTask.setTitle(titleEdited.getText());
                            currentTask.setDescription(descEdited.getText());
                            currentTask.setDate(dateEdited.getValue());
                            currentTask.setPriority(PriorityEdited.getSelectionModel().getSelectedIndex());
                            for(Node node : categoriesEdited.getChildren()){
                                if(node instanceof CheckBox){
                                    CheckBox checkBox = (CheckBox) node;
                                    if(checkBox.isSelected()){
                                        currentTask.getCategory().add(checkBox.getText());
                                    }
                                    else{
                                        currentTask.getCategory().remove(checkBox.getText());
                                    }
                                }
                            }
                            System.out.println(currentTask.getCategory());
                            titleTask.setText(currentTask.getTitle());
                            dateTask.setText(currentTask.getDate().toString());
                            try{
                                switch(currentTask.getPriority()){
                                    case -1:
                                        priorityTaskColor.setStyle("-fx-background-color: gray;");
                                        break;
                                    case 0:
                                        priorityTaskColor.setStyle("-fx-background-color: #00FF00");
                                        break;
                                    case 1:
                                        priorityTaskColor.setStyle("-fx-background-color: #FFFF00;");
                                        break;
                                    case 2:
                                        priorityTaskColor.setStyle("-fx-background-color: #FF0000;");
                                        break;
                                }
                            }catch(Exception ex){
                                System.out.println(ex);
                            }
                            
                            stage.close();
                        });
                        cancelEditedButton.setOnAction((ev) -> {
                            stage.close();
                        });
                        

                        stage.show();

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
                
                lastElement = this;
                isSelected = true;
            }
            else{

            }
        });
        titleTask.setText(task.getTitle());
        dateTask.setText(task.getDate().toString());
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
        this.getChildren().add(root);
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
}
