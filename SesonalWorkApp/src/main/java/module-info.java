module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires com.google.gson;
    requires com.jfoenix;
    requires java.scripting;

    opens Controller;
    opens Model;
    opens Launcher;
    exports Controller to javafx.controls, javafx.graphics, javafx.fxml;
    exports Model to javafx.controls, javafx.graphics, javafx.fxml;
    exports Launcher to javafx.controls, javafx.graphics, javafx.fxml;
}