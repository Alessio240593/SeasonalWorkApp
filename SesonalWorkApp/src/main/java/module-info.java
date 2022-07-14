module Main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires com.google.gson;
    requires com.jfoenix;

    opens Control;
    opens Model;
    opens Launcher;

    exports Launcher to javafx.graphics, javafx.base, javafx.controls;
    exports Model to javafx.graphics, javafx.base, javafx.controls;
    exports Control to javafx.graphics, javafx.base, javafx.controls;
}