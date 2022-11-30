package root;
import java.io.IOException;
import javafx.fxml.*;
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
}
