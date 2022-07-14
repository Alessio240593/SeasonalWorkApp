package Model;

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
        Parent content = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Login.fxml"));
            content = loader.load();

            Scene scene = new Scene(content, 850, 900);

            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon/icon.png"))));
            stage.setResizable(false);
            stage.setTitle("SeasonalWorkApp");
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
