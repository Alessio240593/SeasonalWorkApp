package Model;

import Controller.Utility;
import javafx.application.Application;
import javafx.stage.Stage;

public class Home extends Application {
    public static void main(String[] args){
        Application.launch(args);
    }

    public void start(Stage stage) throws Exception {
        Utility.changeScene("Login.fxml", stage);
    }

}
