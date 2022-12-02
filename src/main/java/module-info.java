module root {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    opens root to javafx.fxml;
    opens root.Views to javafx.fxml;
    opens root.Objects to javafx.fxml;
    exports root;
}
