module root {
    requires javafx.controls;
    requires javafx.fxml;

    opens root to javafx.fxml;
    opens root.Views to javafx.fxml;
    opens root.Objects to javafx.fxml;
    exports root;
}
