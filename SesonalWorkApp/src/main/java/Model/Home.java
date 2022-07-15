package Model;

import Control.Utility;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Home extends Application {
    public static void main(String[] args){
        Application.launch(args);
    }

    public void start(Stage stage) throws Exception {
        Utility.changeScene("Login.fxml", stage);
    }

}
