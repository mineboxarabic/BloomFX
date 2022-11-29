package root;
import java.io.IOException;
import javafx.fxml.*;

public class Main {
    @FXML
    private void switchToSecondary() throws IOException {
        System.out.println("Switching to secondary");
    }
    @FXML
    private void switchToListView() throws IOException {
        System.out.println("Switching to primary");
    }
}
