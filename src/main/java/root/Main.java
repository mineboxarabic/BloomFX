package root;
import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class Main {
    @FXML
    Button but;
    @FXML
    private void switchToSecondary() throws IOException {
        System.out.println("Switching to secondary");
        
    }
    @FXML
    private void switchToListView() throws IOException {
        App.setRoot("listView");
    }
    @FXML
    private void switchToDayView() throws IOException {
        Scene scene = App.getScene();
        //change width and height of scene
        scene.getWindow().setWidth(1200);
        scene.getWindow().setHeight(700);
        App.setRoot("DayView");
    }
    @FXML
    private void switchToCalendarView() throws IOException{
        Scene scene = App.getScene();
        //change width and height of scene
        scene.getWindow().setWidth(1300);
        scene.getWindow().setHeight(800);
        try{
            App.setRoot("CalendarView");
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
}
