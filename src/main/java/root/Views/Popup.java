package root.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import root.Objects.Task;
import root.Objects.listViewElement;

public class Popup extends Stage
{
    Scene scene;
    public Popup() throws Exception
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("uiFXML/Popup.fxml"));
            Parent root = fxmlLoader.load();
            scene = new Scene(root);
            this.setScene(scene);
            this.show();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
   public Popup (listViewElement element){
        show();
        Task task = element.getTask();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("uiFXML/Popup.fxml"));
            Parent root = fxmlLoader.load();
            scene = new Scene(root);
            this.setScene(scene);
            this.show();
        }
        catch(Exception e){
            System.out.println(e);
        }
        Button saveButton = (Button) scene.lookup("#saveButton");
        Button cancelButton = (Button) scene.lookup("#cancelButton");
        TextField titleField = (TextField) scene.lookup("#titleField");
        TextArea DescArea = (TextArea) scene.lookup("#DescArea");
        DatePicker datePicker = (DatePicker) scene.lookup("#datePicker");
        ComboBox<String> priorityComboBox = (ComboBox) scene.lookup("#priorityComboBox");
        priorityComboBox.getItems().addAll("Low", "Medium", "High");

        titleField.setText(task.getTitle());
        DescArea.setText(task.getDescription());
        datePicker.setValue(task.getDate());
        priorityComboBox.selectionModelProperty().get().select(task.getPriority());
        

        saveButton.setOnAction((e) ->{
            try{
                Task newTask = new Task(titleField.getText(), DescArea.getText(), datePicker.getValue(), "12:00", priorityComboBox.getSelectionModel().getSelectedIndex(), "Work");
                element.updateElement(newTask);
            }
            catch(Exception ex){
                System.out.println(ex);
            }
            
            this.close();
        });
        cancelButton.setOnAction((e) ->{
            this.close();
        });
   }
}
