package root.Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class calendarViewElement extends VBox
{
    double start_x = 0;
    double start_y = 0;
    
    public calendarViewElement() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("uiFXML/CalendarViewElement.fxml"));
        Parent root = loader.load();
        getChildren().add(root);
        //Make this element gragable


    }
}
