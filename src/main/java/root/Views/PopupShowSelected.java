package root.Views;

import java.util.Vector;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import root.Objects.dayViewElement;

public class PopupShowSelected extends Stage
{
    public PopupShowSelected(Vector<dayViewElement> elements)
    {
        VBox root = new VBox();
        Scene scene = new Scene(root, 600, 300);
        setScene(scene);

        for(dayViewElement element : elements)
        {
            root.getChildren().add(element);
        }

        show();
    }
}
