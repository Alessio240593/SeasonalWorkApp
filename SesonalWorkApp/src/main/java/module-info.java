module Main {
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
}