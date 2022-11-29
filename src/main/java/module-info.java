module root {
    requires javafx.controls;
    requires javafx.fxml;

    opens root to javafx.fxml;
    exports root;
}
