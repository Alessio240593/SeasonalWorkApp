module Main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires com.google.gson;

    opens Control;
    opens Model;
    opens Launcher;

}